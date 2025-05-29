package alyona.forma.service.history

import alyona.forma.exception.EntityNotFoundException
import alyona.forma.model.history.BaseTrainingHistory
import alyona.forma.model.training.BaseTraining
import alyona.forma.model.trainingconst.TrStatus
import alyona.forma.model.user.User
import alyona.forma.repository.BaseTrainingHistoryRepository
import alyona.forma.repository.BaseTrainingRepository
import alyona.forma.repository.TrStatusRepository
import org.springframework.stereotype.Service
import java.sql.Time
import java.time.Instant
import java.util.UUID

@Service
class BaseTrainingHistoryService(
    private val baseTrainingHistoryRepository: BaseTrainingHistoryRepository,
    private val baseTrainingRepository: BaseTrainingRepository,
    private val trStatusRepository: TrStatusRepository
) {

    fun planTraining(
        user: User,
        trainingId: UUID,
        dateTime: Instant
    ) {
        val training = baseTrainingRepository.findById(trainingId).orElseThrow { EntityNotFoundException("Training not found with id: $trainingId") }

        val planingStatus = trStatusRepository.findTrStatusByName(TrStatus.TrStatusName.PLANING)

    }

    private fun mapToHistory(baseTraining: BaseTraining): BaseTrainingHistory {
        val bth = BaseTrainingHistory()
        bth.name = baseTraining.name
        bth.description = baseTraining.description

//        bth.baseExToPositions = baseTraining.baseExercise
//        bth.trainingDate = baseTraining.trainingDate
//        bth.trainingTime = baseTraining.trainingTime
//        return bth
        return bth
    }

}