package alyona.forma.config.security

import alyona.forma.repository.reposervice.UserRepoService
import alyona.forma.security.JWTUtil
import alyona.forma.security.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration

@Configuration
class WebSecurityConfig(
    private val jwtUtil: JWTUtil,
    private val userRepoService: UserRepoService
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/auth/login", "/auth/registration", "/training-level", "/base-trainings/min").permitAll()
                    .anyRequest().authenticated()
            }
            .headers { httpSecurityHeadersConfigurer ->
                httpSecurityHeadersConfigurer.frameOptions {
                    it.sameOrigin()
                }
            }
            .cors {
                it.configurationSource {
                    val configuration = CorsConfiguration()
                    configuration.allowedOrigins = listOf("http://localhost:4200")
                    configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                    configuration.allowCredentials = false
                    configuration.allowedHeaders = listOf("*")
                    configuration
                }
            }
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun getPasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun jwtAuthenticationFilter() = JwtAuthenticationFilter(jwtUtil, userRepoService)
}