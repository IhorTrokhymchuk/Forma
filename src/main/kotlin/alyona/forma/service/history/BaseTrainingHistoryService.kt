package alyona.forma.service.history

import alyona.forma.exception.EntityNotFoundException
import alyona.forma.model.history.BaseExToPositionHistory
import alyona.forma.model.history.BaseSetHistory
import alyona.forma.model.history.BaseTrainingHistory
import alyona.forma.model.training.BaseTraining
import alyona.forma.model.trainingconst.TrStatus
import alyona.forma.model.user.User
import alyona.forma.repository.BaseTrainingHistoryRepository
import alyona.forma.repository.TrStatusRepository
import alyona.forma.util.TrainingCoefficientsUtil
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.UUID
import kotlin.math.round

@Service
class BaseTrainingHistoryService(
    private val baseTrainingHistoryRepository: BaseTrainingHistoryRepository,
    private val trStatusRepository: TrStatusRepository,
) {
    fun findAllByUser(user: User): List<BaseTrainingHistory> {
        return user.id?.let { baseTrainingHistoryRepository.findByUser_Id(it) }
            ?: throw EntityNotFoundException("User not found")
    }
    
    fun findById(id: UUID): BaseTrainingHistory {
        return baseTrainingHistoryRepository.findById(id)
            .orElseThrow { EntityNotFoundException("BaseTrainingHistory with id $id not found") }
    }

    @Transactional
    fun planTraining(
        user: User,
        trainingDate: Instant,
    ) {
        val baseTraining = user.baseTraining
        val resKoef = TrainingCoefficientsUtil.calculateResistanceCoefficient(user)
        val daysPerWeek = user.daysPerWeek
        val baseTrainingHistories = mapToHistory(baseTraining, trainingDate, resKoef, daysPerWeek, user)
        baseTrainingHistoryRepository.saveAll(baseTrainingHistories)
    }

    private fun mapToHistory(
        baseTraining: BaseTraining,
        trainingDate: Instant,
        resKoef: Double,
        daysPerWeek: Long,
        user: User
    ): MutableList<BaseTrainingHistory> {
        val planingStatus = trStatusRepository.findTrStatusByName(TrStatus.TrStatusName.PLANING)
       //todo: for May use
        val dates = getDates(trainingDate.plusSeconds(60 * 60 * 24 * 5), daysPerWeek)
        return dates.map { date ->
            val bth = BaseTrainingHistory()
            bth.name = baseTraining.name
            bth.description = baseTraining.description
            bth.dateTime = date
            bth.trStatus = planingStatus
            bth.user = user
            bth.baseExToPositions = baseTraining.baseExToPositions.map { betp ->
                BaseExToPositionHistory().apply {
                    this.baseExercise = betp.baseExercise
                    this.position = betp.position
                    this.baseSets = betp.baseSets.map { bs ->
                        val baseSetHistory = BaseSetHistory()
                        baseSetHistory.reps = bs.reps
                        baseSetHistory.position = bs.position
                        baseSetHistory.kg = round(bs.kg * resKoef).toLong()
                        baseSetHistory
                    }.toMutableList()
                    this.trStatus = planingStatus
                }
            }.toMutableList()
            bth
        }.toMutableList()
    }

    private fun getDates (
        trainingDate: Instant,
        daysPerWeek: Long
    ): List<Instant> {
        val zoneId = ZoneId.systemDefault()
        val today = LocalDate.ofInstant(trainingDate, zoneId)
        val endOfMonth = today.withDayOfMonth(today.lengthOfMonth())

        val trainingDays = when (daysPerWeek) {
            2L -> listOf(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY)
            3L -> listOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY)
            else -> throw Exception("Unknown days per week")
        }

        val result = mutableListOf<Instant>()

        var date = today
        while (!date.isAfter(endOfMonth)) {
            if (trainingDays.contains(date.dayOfWeek)) {
                result.add(date.atStartOfDay(zoneId).plusHours(19).toInstant())
            }
            date = date.plusDays(1)
        }

        return result
    }

}