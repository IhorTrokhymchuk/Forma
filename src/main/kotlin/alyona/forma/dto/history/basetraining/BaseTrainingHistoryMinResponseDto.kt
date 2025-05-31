package alyona.forma.dto.history.basetraining

import java.time.Instant
import java.time.LocalDate
import java.util.UUID

class BaseTrainingHistoryMinResponseDto(
    val id: UUID,
    val name: String,
    val dateTime: LocalDate,
    val trStatusDisplayName: String
)