package alyona.forma.dto.baseextoposition

import java.util.*

class BaseExToPositionRequestDto(
    val baseExerciseId: UUID,
    val position: Long,
    val baseSetIds: List<UUID>
)