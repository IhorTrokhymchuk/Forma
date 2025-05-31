package alyona.forma.dto.history.baseextoposition

import alyona.forma.dto.baseex.BaseExerciseResponseDto
import alyona.forma.dto.history.baseset.BaseSetHistoryResponseDto
import java.util.*

class BaseExToPositionHistoryResponseDto(
    val id: UUID,
    val baseExercise: BaseExerciseResponseDto,
    val position: Long,
    val baseSets: List<BaseSetHistoryResponseDto>,
    val trStatusDisplayName: String
)
