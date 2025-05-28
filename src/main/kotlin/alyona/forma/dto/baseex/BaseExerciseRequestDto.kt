package alyona.forma.dto.baseex

import java.util.*

class BaseExerciseRequestDto(
    val name: String,
    val description: String?,
    val muscleGroupId: UUID,
    val videoUrl: String,
    val mainImage: String?,
    val miniImage: String?,
)