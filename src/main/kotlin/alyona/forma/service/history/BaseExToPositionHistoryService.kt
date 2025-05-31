package alyona.forma.service.history

import alyona.forma.dto.history.baseset.BaseSetHistoryRequestDto
import alyona.forma.dto.history.baseset.BaseSetHistoryResponseDto
import alyona.forma.exception.EntityNotFoundException
import alyona.forma.model.history.BaseExToPositionHistory
import alyona.forma.model.trainingconst.TrStatus
import alyona.forma.repository.BaseExToPositionHistoryRepository
import alyona.forma.repository.TrStatusRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BaseExToPositionHistoryService(
    private val baseExToPositionHistoryRepository: BaseExToPositionHistoryRepository,
    private val trStatusRepository: TrStatusRepository
) {

    @Transactional
    fun completeExToPosition(
        id: UUID,
        baseSetHistoriesRequest: List<BaseSetHistoryRequestDto>
    ) : BaseExToPositionHistory {
        val baseExToPositionHistory = baseExToPositionHistoryRepository.findById(id).orElseThrow {
            EntityNotFoundException("BaseExToPositionHistory with id $id not found")
        }
        baseExToPositionHistory.baseSets.forEach { bsh ->
            val requestDto = baseSetHistoriesRequest.find { bshr ->
                bsh.id == bshr.id
            } ?: throw EntityNotFoundException("BaseSetHistory with id ${bsh.id} not found from request")
            bsh.kg = requestDto.kg
            bsh.reps = requestDto.reps
        }
        baseExToPositionHistory.trStatus = trStatusRepository.findTrStatusByName(TrStatus.TrStatusName.COMPLETED)
        return baseExToPositionHistoryRepository.save(baseExToPositionHistory)
    }
}