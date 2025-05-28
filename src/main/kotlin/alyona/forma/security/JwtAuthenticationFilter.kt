package alyona.forma.security

import alyona.forma.repository.reposervice.UserRepoService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.time.Instant
import kotlin.run
import kotlin.text.startsWith
import kotlin.text.substring

@Component
class JwtAuthenticationFilter(
    private val jwtUtil: JWTUtil,
    private val userRepoService: UserRepoService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = getTokenFromRequest(request,response)
        token?.run {
            val expiration = jwtUtil.expiration(token)
            if (expiration.isBefore(Instant.now())) {
                throw Exception("Token expired")
            }
            val email = jwtUtil.email(token)
            val user = userRepoService.getUserByEmail(email)
            val authentication = UsernamePasswordAuthenticationToken(user, null, user.roles)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun getTokenFromRequest(request: HttpServletRequest, response: HttpServletResponse): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else {
            val parameter = request.getParameter("Token")
            return if (StringUtils.hasText(parameter)) {
                response.addHeader("Access-Control-Allow-Credentials", "true")
                parameter
            } else
                null
        }
    }
}