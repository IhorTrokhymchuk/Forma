package alyona.forma.dto.history.baseset

import java.util.UUID

data class BaseSetHistoryRequestDto(
    val id: UUID,
    val kg: Long,
    val reps: Long
)
