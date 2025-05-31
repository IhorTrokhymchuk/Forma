package alyona.forma.service.history

import alyona.forma.dto.statistic.StatusCountDto
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
    fun startTraining(id: UUID) : BaseTrainingHistory {
        val trainingHistory = baseTrainingHistoryRepository.findById(id)
            .orElseThrow { EntityNotFoundException("BaseTrainingHistory with id $id not found") }
        val progresStatus = trStatusRepository.findTrStatusByName(TrStatus.TrStatusName.IN_PROGRESS)
        trainingHistory.trStatus = progresStatus
        trainingHistory.baseExToPositions.forEach {
            it.trStatus = progresStatus
        }
        return baseTrainingHistoryRepository.save(trainingHistory)
    }

    fun finishTraining(id: UUID) : BaseTrainingHistory {
        val trainingHistory = baseTrainingHistoryRepository.findById(id)
            .orElseThrow { EntityNotFoundException("BaseTrainingHistory with id $id not found") }
        trainingHistory.baseExToPositions.forEach { exToPos ->
            if (exToPos.trStatus.name != TrStatus.TrStatusName.COMPLETED)
                throw RuntimeException("Training is not completed")
        }
        trainingHistory.trStatus = trStatusRepository.findTrStatusByName(TrStatus.TrStatusName.COMPLETED)
        return baseTrainingHistoryRepository.save(trainingHistory)
    }

    fun statisticByStatus(user: User, from: LocalDate, to: LocalDate): List<StatusCountDto> {
        return user.id?.let {
            baseTrainingHistoryRepository.countByStatusDisplayNameBetweenDates(it, from, to)
        } ?: throw EntityNotFoundException("User not found")
    }

    fun findLastCompletedByUser(user: User): BaseTrainingHistory {
        return user.id?.let {
            baseTrainingHistoryRepository.findLastByUserIdAndTrStatusName(it, TrStatus.TrStatusName.COMPLETED)
                .orElseThrow { EntityNotFoundException("BaseTrainingHistory not found") }
        } ?: throw EntityNotFoundException("User not found")
    }
    fun findAllByUserAndFromTo(user: User, from: LocalDate, to: LocalDate): List<BaseTrainingHistory> {
        return user.id?.let {
            baseTrainingHistoryRepository.findByUserIdAndDates(it, from, to)
        } ?: throw EntityNotFoundException("User not found")
    }


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
        trainingDate: LocalDate,
    ) {
        val baseTraining = user.baseTraining
        val resKoef = TrainingCoefficientsUtil.calculateResistanceCoefficient(user)
        val daysPerWeek = user.daysPerWeek
        val baseTrainingHistories = mapToHistory(baseTraining, trainingDate, resKoef, daysPerWeek, user)
        baseTrainingHistoryRepository.saveAll(baseTrainingHistories)
    }

    private fun mapToHistory(
        baseTraining: BaseTraining,
        trainingDate: LocalDate,
        resKoef: Double,
        daysPerWeek: Long,
        user: User
    ): MutableList<BaseTrainingHistory> {
        val planingStatus = trStatusRepository.findTrStatusByName(TrStatus.TrStatusName.PLANING)
       //todo: for May use
        val dates = getDates(trainingDate, daysPerWeek)
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
        today: LocalDate,
        daysPerWeek: Long
    ): List<LocalDate> {
        val endOfMonth = today.withDayOfMonth(today.lengthOfMonth())

        val trainingDays = when (daysPerWeek) {
            2L -> listOf(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY)
            3L -> listOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY)
            else -> throw Exception("Unknown days per week")
        }

        val result = mutableListOf<LocalDate>()

        var date = today
        while (!date.isAfter(endOfMonth)) {
            if (trainingDays.contains(date.dayOfWeek)) {
                result.add(date)
            }
            date = date.plusDays(1)
        }

        return result
    }

    fun weightStatisticByUserAndDate(user: User, from: LocalDate, to: LocalDate): MutableMap<LocalDate, Long> {
         return user.id?.let {
             val copletedTrainings = baseTrainingHistoryRepository.findByUserIdAndDatesAndStatusName(it, from, to, TrStatus.TrStatusName.COMPLETED)
             val resultMap = mutableMapOf<LocalDate, Long>()
             copletedTrainings.forEach { training ->
                 val date = training.dateTime
                 val totalWeight = training.baseExToPositions
                     .filter { exToPos -> exToPos.trStatus.name == TrStatus.TrStatusName.COMPLETED }
                     .sumOf { exToPos ->
                         exToPos.baseSets.sumOf { bs ->
                             bs.kg * bs.reps
                         }
                     }
                 resultMap.put(date, totalWeight)
             }
             resultMap
         } ?: throw EntityNotFoundException("User not found")
    }

}