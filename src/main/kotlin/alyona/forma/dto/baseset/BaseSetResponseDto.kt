package alyona.forma.dto.baseset

import java.util.UUID

data class BaseSetResponseDto(
    val id: UUID,
    val kg: Long,
    val reps: Long,
    val position: Long,
)