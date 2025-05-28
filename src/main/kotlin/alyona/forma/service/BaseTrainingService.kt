package alyona.forma.service

import alyona.forma.exception.EntityNotFoundException
import alyona.forma.model.training.BaseTraining
import alyona.forma.repository.BaseTrainingRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BaseTrainingService(
    private val baseTrainingRepository: BaseTrainingRepository
) {
    fun findAll(pageable: Pageable): Page<BaseTraining> = baseTrainingRepository.findAll(pageable)
    
    fun findById(id: UUID): BaseTraining = baseTrainingRepository.findById(id)
        .orElseThrow { EntityNotFoundException("BaseTraining not found with id: $id") }

    @Transactional
    fun save(baseTraining: BaseTraining): BaseTraining {
        baseTraining.baseExToPositions.forEach { baseExToPosition ->
            println(baseExToPosition.id)
        }
        return baseTrainingRepository.save(baseTraining)
    }
    
    @Transactional
    fun deleteById(id: UUID) {
        if (!baseTrainingRepository.existsById(id)) {
            throw EntityNotFoundException("BaseTraining not found with id: $id")
        }
        baseTrainingRepository.deleteById(id)
    }
}
