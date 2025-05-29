package alyona.forma.dto.history.basetraining

import java.util.*

class BaseTrainingRequestDto(
    val name: String,
    val description: String?,
    val baseExToPositionIds: List<UUID>
)