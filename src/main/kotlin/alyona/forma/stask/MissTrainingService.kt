package alyona.forma.stask

import alyona.forma.model.trainingconst.TrStatus
import alyona.forma.repository.BaseTrainingHistoryRepository
import alyona.forma.repository.TrStatusRepository
import jakarta.transaction.Transactional
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MissTrainingService(
    private val baseTrainingHistoryRepository: BaseTrainingHistoryRepository,
    private val trStatusRepository: TrStatusRepository
) {

    @Scheduled(cron = "0 0 23 * * *", zone = "Europe/Kyiv")
    @Transactional
    fun missTraining() {
        val missedStatus = trStatusRepository.findTrStatusByName(TrStatus.TrStatusName.MISSED)
        val missedTraining = baseTrainingHistoryRepository.findAllByDateTimeAndTrStatus_Name(
            LocalDate.now(),
            TrStatus.TrStatusName.PLANING
        )
        missedTraining.forEach { mt ->
            mt.trStatus = missedStatus
            mt.baseExToPositions.forEach { betp ->
                betp.trStatus = missedStatus
            }
        }
        baseTrainingHistoryRepository.saveAll(missedTraining)
    }
}