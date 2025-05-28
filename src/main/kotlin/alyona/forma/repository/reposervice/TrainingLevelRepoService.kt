package alyona.forma.repository.reposervice

import alyona.forma.exception.EntityNotFoundException
import alyona.forma.model.TrainingLevel
import alyona.forma.repository.TrainingLevelRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TrainingLevelRepoService(
    private val trainingLevelRepository: TrainingLevelRepository
) {
    fun getById(id: UUID) : TrainingLevel = trainingLevelRepository.findById(id)
        .orElseThrow { EntityNotFoundException("Training level with id $id not found") }
}