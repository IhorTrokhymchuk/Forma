package alyona.forma.security

import alyona.forma.repository.reposervice.UserRepoService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomUserDetailsService(
    private val userRepoService: UserRepoService
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return userRepoService.getUserByEmail(username)
    }
}