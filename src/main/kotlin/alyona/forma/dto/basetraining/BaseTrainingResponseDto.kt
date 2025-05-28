package alyona.forma.dto.basetraining

import alyona.forma.dto.baseextoposition.BaseExToPositionResponseDto
import java.util.UUID

class BaseTrainingResponseDto(
    val name: String,
    val trainingLevelDisplayName: String,
    val description: String?,
    val baseExToPositions: List<BaseExToPositionResponseDto>
)