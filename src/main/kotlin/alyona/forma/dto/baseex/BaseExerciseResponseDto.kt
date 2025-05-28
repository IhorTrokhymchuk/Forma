package alyona.forma.dto.baseex

import alyona.forma.dto.baseset.BaseSetResponseDto

class BaseExerciseResponseDto(
    val id: Long,
    val name: String,
    val description: String?,
    val muscleGroupName: String,
    val position: Long,
    val videoUrl: String,
    val mainImage: String?,
    val miniImage: String?,
    val baseSets: MutableList<BaseSetResponseDto>
)