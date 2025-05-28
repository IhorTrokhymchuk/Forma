package alyona.forma.dto.user

import java.time.Instant
import java.util.UUID

data class UserResponseDto(
    val id: UUID,
    val email: String,
    val firstName: String,
    val lastName: String,
    val trainingLevelDisplayName: String,
    val roles: List<RoleTypeResponseDto>,
    val lastLogin: Instant,
    val age: Long,
    val weight: Long,
    val gender: String
)