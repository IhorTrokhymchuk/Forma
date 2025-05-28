package alyona.forma.repository.reposervice

import alyona.forma.exception.EntityNotFoundException
import alyona.forma.model.user.User
import alyona.forma.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserRepoService(
    private val userRepository: UserRepository
) {
    fun getUserByEmail(email: String): User {
        return userRepository.findByEmail(email)
            .orElseThrow { EntityNotFoundException("User with email $email not found") }
    }

}