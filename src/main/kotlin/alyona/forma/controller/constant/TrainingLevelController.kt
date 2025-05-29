package alyona.forma.controller.constant

import alyona.forma.repository.TrainingLevelRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/training-level")
class TrainingLevelController(
    private val trainingLevelRepository: TrainingLevelRepository
) {
    @GetMapping
    fun findAll() = trainingLevelRepository.findAll()
}