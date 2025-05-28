package alyona.forma.dto.baseex

import alyona.forma.dto.baseset.BaseSetResponseDto
import java.util.UUID

data class BaseExerciseResponseDto(
    val id: UUID,
    val name: String,
    val description: String?,
    val muscleGroupName: String,
    val videoUrl: String,
    val mainImage: String?,
    val miniImage: String?,
)