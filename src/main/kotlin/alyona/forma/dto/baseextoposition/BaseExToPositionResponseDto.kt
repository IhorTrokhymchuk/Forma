package alyona.forma.dto.baseextoposition

import alyona.forma.dto.baseex.BaseExerciseResponseDto
import alyona.forma.dto.baseset.BaseSetResponseDto
import java.util.UUID

class BaseExToPositionResponseDto(
    val id: UUID,
    val baseExercise: BaseExerciseResponseDto,
    val position: Long,
    val baseSets: List<BaseSetResponseDto>

)