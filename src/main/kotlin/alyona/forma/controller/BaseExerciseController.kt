package alyona.forma.controller

import alyona.forma.dto.baseex.BaseExerciseRequestDto
import alyona.forma.dto.baseex.BaseExerciseResponseDto
import alyona.forma.mapper.BaseExerciseMapper
import alyona.forma.service.BaseExerciseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/base-exercises")
class BaseExerciseController(
    private val baseExerciseService: BaseExerciseService,
    private val baseExerciseMapper: BaseExerciseMapper
) {

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    fun findAll(pageable: Pageable): Page<BaseExerciseResponseDto> {
        return baseExerciseService.findAll(pageable)
            .map { baseExerciseMapper.toBaseExerciseResponseDto(it) }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    fun findById(@PathVariable id: UUID): BaseExerciseResponseDto {
        val baseExercise = baseExerciseService.findById(id)
        return baseExerciseMapper.toBaseExerciseResponseDto(baseExercise)
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody requestDto: BaseExerciseRequestDto): BaseExerciseResponseDto {
        val baseExercise = baseExerciseMapper.toBaseExercise(requestDto)
        val savedBaseExercise = baseExerciseService.save(baseExercise)
        return baseExerciseMapper.toBaseExerciseResponseDto(savedBaseExercise)
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) {
        baseExerciseService.deleteById(id)
    }
}
