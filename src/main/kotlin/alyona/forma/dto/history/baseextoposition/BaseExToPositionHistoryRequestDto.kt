package alyona.forma.dto.history.baseextoposition

import java.util.*

class BaseExToPositionHistoryRequestDto(
    val baseExerciseId: UUID,
    val position: Long,
    val baseSetIds: List<UUID>
)
