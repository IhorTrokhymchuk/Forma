package alyona.forma.dto.history.basetraining

import alyona.forma.dto.baseextoposition.BaseExToPositionResponseDto
import alyona.forma.dto.history.baseextoposition.BaseExToPositionHistoryResponseDto
import alyona.forma.model.history.BaseExToPositionHistory
import alyona.forma.model.trainingconst.TrStatus
import java.time.Instant
import java.time.LocalDate
import java.util.UUID

class BaseTrainingHistoryResponseDto(
    val id: UUID,
    val name: String,
    val description: String?,
    val baseExToPositions: List<BaseExToPositionHistoryResponseDto>,
    val dateTime: LocalDate,
    val trStatusDisplayName: String
)