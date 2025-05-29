package alyona.forma.dto.history.baseset

import java.util.*

data class BaseSetResponseDto(
    val id: UUID,
    val kg: Long,
    val reps: Long,
    val position: Long,
)