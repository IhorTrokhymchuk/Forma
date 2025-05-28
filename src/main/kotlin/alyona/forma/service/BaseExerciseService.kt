package alyona.forma.service

import alyona.forma.exception.EntityExistException
import alyona.forma.exception.EntityNotFoundException
import alyona.forma.model.training.BaseExercise
import alyona.forma.repository.BaseExerciseRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class BaseExerciseService(
    private val baseExerciseRepository: BaseExerciseRepository
) {
    fun findAll(pageable: Pageable): Page<BaseExercise> = baseExerciseRepository.findAll(pageable)
    
    fun findById(id: UUID): BaseExercise = baseExerciseRepository.findById(id)
        .orElseThrow { EntityNotFoundException("BaseExercise not found with id: $id") }
    
    @Transactional
    fun save(baseExercise: BaseExercise): BaseExercise {
        if (baseExerciseRepository.existsBaseExerciseByName(baseExercise.name))
            throw EntityExistException("BaseExercise with name ${baseExercise.name} already exists")

        return baseExerciseRepository.save(baseExercise)
    }
    
    @Transactional
    fun deleteById(id: UUID) = baseExerciseRepository.deleteById(id)
}
