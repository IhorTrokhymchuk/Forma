package alyona.forma.service

import alyona.forma.exception.EntityNotFoundException
import alyona.forma.model.training.BaseSet
import alyona.forma.repository.BaseSetRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BaseSetService(
    private val baseSetRepository: BaseSetRepository
) {
    
    fun findAll(): List<BaseSet> = baseSetRepository.findAll()
    
    fun findById(id: UUID): BaseSet = baseSetRepository.findById(id)
        .orElseThrow { EntityNotFoundException("BaseSet not found with id: $id") }
    
    @Transactional
    fun save(baseSet: BaseSet): BaseSet = baseSetRepository.save(baseSet)
    
    @Transactional
    fun deleteById(id: UUID) = baseSetRepository.deleteById(id)
}
