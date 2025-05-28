package alyona.forma.controller

import alyona.forma.dto.baseextoposition.BaseExToPositionRequestDto
import alyona.forma.dto.baseextoposition.BaseExToPositionResponseDto
import alyona.forma.mapper.BaseExToPositionMapper
import alyona.forma.service.BaseExToPositionService
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
@RequestMapping("/base-ex-to-positions")
class BaseExToPositionController(
    private val baseExToPositionService: BaseExToPositionService,
    private val baseExToPositionMapper: BaseExToPositionMapper
) {
    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    fun findAll(pageable: Pageable): Page<BaseExToPositionResponseDto> {
        return baseExToPositionService.findAll(pageable)
            .map { baseExToPositionMapper.toBaseExToPositionResponseDto(it) }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    fun findById(@PathVariable id: UUID): BaseExToPositionResponseDto {
        val baseExToPosition = baseExToPositionService.findById(id)
        return baseExToPositionMapper.toBaseExToPositionResponseDto(baseExToPosition)
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody requestDto: BaseExToPositionRequestDto): BaseExToPositionResponseDto {
        val baseExToPosition = baseExToPositionMapper.toBaseExToPosition(requestDto)
        val savedBaseExToPosition = baseExToPositionService.save(baseExToPosition)
        return baseExToPositionMapper.toBaseExToPositionResponseDto(savedBaseExToPosition)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) {
        baseExToPositionService.deleteById(id)
    }
}
