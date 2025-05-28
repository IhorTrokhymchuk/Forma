package alyona.forma.dto.user

data class UserLoginResponseDto(
    val jwtToken: String,
    val user: UserResponseDto
)