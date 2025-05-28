package alyona.forma.dto.basetraining

import java.util.UUID

class BaseTrainingRequestDto(
    val name: String,
    val trainingLevelId: UUID,
    val description: String?,
    val baseExToPositionIds: List<UUID>
)