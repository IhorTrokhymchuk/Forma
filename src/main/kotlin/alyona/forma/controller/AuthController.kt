package alyona.forma.controller

import alyona.forma.dto.auth.UseeLoginDto
import alyona.forma.dto.auth.UserRegisterDto
import alyona.forma.dto.user.UserLoginResponseDto
import alyona.forma.mapper.UserMapper
import alyona.forma.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
    private val userMapper: UserMapper
) {

    @PostMapping("/registration")
    fun registration(
        @RequestBody request: UserRegisterDto
    ) : Boolean =authService.registration(request)


    @PostMapping("/login")
    fun login(
        @RequestBody request: UseeLoginDto
    ): UserLoginResponseDto {
        val userToJwt = authService.login(request)
        val userResponse = userMapper.toUserResponseDto(userToJwt.first)
        return UserLoginResponseDto(userToJwt.second, userResponse)
    }
}