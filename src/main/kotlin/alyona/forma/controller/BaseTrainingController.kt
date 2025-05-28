package alyona.forma.controller

import alyona.forma.dto.basetraining.BaseTrainingRequestDto
import alyona.forma.dto.basetraining.BaseTrainingResponseDto
import alyona.forma.mapper.BaseTrainingMapper
import alyona.forma.service.BaseTrainingService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/base-trainings")
class BaseTrainingController(
    private val baseTrainingService: BaseTrainingService,
    private val baseTrainingMapper: BaseTrainingMapper
) {
    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    fun findAll(pageable: Pageable): Page<BaseTrainingResponseDto> {
        return baseTrainingService.findAll(pageable)
            .map { baseTrainingMapper.toBaseTrainingResponseDto(it) }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    fun findById(@PathVariable id: UUID): BaseTrainingResponseDto {
        val baseTraining = baseTrainingService.findById(id)
        return baseTrainingMapper.toBaseTrainingResponseDto(baseTraining)
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody requestDto: BaseTrainingRequestDto): BaseTrainingResponseDto {
        println("-----controller start ")
        requestDto.baseExToPositionIds.forEach { id ->
            println(id)
        }
        println("-----controller end ")

        val baseTraining = baseTrainingMapper.toBaseTraining(requestDto)
        val savedBaseTraining = baseTrainingService.save(baseTraining)
        return baseTrainingMapper.toBaseTrainingResponseDto(savedBaseTraining)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) {
        baseTrainingService.deleteById(id)
    }
}
