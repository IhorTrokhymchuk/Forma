package alyona.forma.dto.auth

import alyona.forma.model.user.User
import java.util.*

data class UserRegisterDto(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val age: Long,
    val gender: User.Gender,
    val trainingLevelId: UUID,
    val weight: Long
)
