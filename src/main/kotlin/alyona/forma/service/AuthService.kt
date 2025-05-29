package alyona.forma.service

import alyona.forma.dto.auth.UseeLoginDto
import alyona.forma.dto.auth.UserRegisterDto
import alyona.forma.exception.EntityExistException
import alyona.forma.exception.EntityNotFoundException
import alyona.forma.model.user.RoleType
import alyona.forma.model.user.User
import alyona.forma.repository.BaseTrainingRepository
import alyona.forma.repository.RoleTypeRepository
import alyona.forma.repository.UserRepository
import alyona.forma.repository.reposervice.TrainingLevelRepoService
import alyona.forma.repository.reposervice.UserRepoService
import alyona.forma.security.JWTUtil
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val userRepoService: UserRepoService,
    private val trainingLevelRepoService: TrainingLevelRepoService,
    private val roleTypeRepository: RoleTypeRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JWTUtil,
    private val baseTrainingRepository: BaseTrainingRepository
) {

    @Transactional
    fun registration(request: UserRegisterDto): Boolean {
        if(userRepository.existsUserByEmail(request.email))
            throw EntityExistException("User with email ${request.email} already exist")

        val trainingLevel = trainingLevelRepoService.getById(request.trainingLevelId)
        val userRole = roleTypeRepository.getRoleTypesByName(RoleType.RoleName.USER)
        val newUser = User().apply {
            email = request.email
            userPassword = passwordEncoder.encode(request.password)
            firstName = request.firstName
            lastName = request.lastName
            roles = mutableSetOf(userRole)
            this.trainingLevel = trainingLevel
            age = request.age
            gender = request.gender
            height = request.height
            weight = request.weight
            lastLogin = Instant.now()
            daysPerWeek = request.daysPerWeek
            baseTraining = baseTrainingRepository.findById(request.baseTrainingId)
                .orElseThrow { EntityNotFoundException("BaseTraining not found with id: ${request.baseTrainingId}") }
        }
        userRepository.save(newUser)

        return true
    }

    @Transactional
    fun login(request: UseeLoginDto): Pair<User, String> {
        val user = userRepoService.getUserByEmail(request.email)
        passwordEncoder.matches(request.password, user.userPassword)
        user.lastLogin = Instant.now()
        userRepository.save(user)
        return user to jwtUtil.getToken(user.email)
    }
}