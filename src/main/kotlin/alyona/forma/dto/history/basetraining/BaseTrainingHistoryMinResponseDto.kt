package alyona.forma.dto.history.basetraining

import java.time.Instant
import java.util.UUID

class BaseTrainingHistoryMinResponseDto(
    val id: UUID,
    val name: String,
    val dateTime: Instant,
    val trStatusDisplayName: String
)