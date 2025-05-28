package alyona.forma.service

import alyona.forma.exception.EntityNotFoundException
import alyona.forma.model.training.BaseExToPosition
import alyona.forma.repository.BaseExToPositionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BaseExToPositionService(
    private val baseExToPositionRepository: BaseExToPositionRepository
) {
    fun findAll(pageable: Pageable): Page<BaseExToPosition> = baseExToPositionRepository.findAll(pageable)
    
    fun findById(id: UUID): BaseExToPosition = baseExToPositionRepository.findById(id)
        .orElseThrow { EntityNotFoundException("BaseExToPosition not found with id: $id") }

    @Transactional
    fun save(baseExToPosition: BaseExToPosition): BaseExToPosition = baseExToPositionRepository.save(baseExToPosition)
    
    @Transactional
    fun deleteById(id: UUID) = baseExToPositionRepository.deleteById(id)
    

}
