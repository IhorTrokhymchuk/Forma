package alyona.forma.dto.history.basetraining

import alyona.forma.dto.baseextoposition.BaseExToPositionResponseDto

class BaseTrainingResponseDto(
    val name: String,
    val description: String?,
    val baseExToPositions: List<BaseExToPositionResponseDto>
)