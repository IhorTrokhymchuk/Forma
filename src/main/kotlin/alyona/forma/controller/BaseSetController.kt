package alyona.forma.controller

import alyona.forma.dto.baseset.BaseSetRequestDto
import alyona.forma.dto.baseset.BaseSetResponseDto
import alyona.forma.mapper.BaseSetMapper
import alyona.forma.service.BaseSetService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/base-sets")
class BaseSetController(
    private val baseSetService: BaseSetService,
    private val baseSetMapper: BaseSetMapper
) {
    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    fun findAll(pageable: Pageable): Page<BaseSetResponseDto> {
        return baseSetService.findAll(pageable)
            .map { baseSetMapper.toBaseSetResponseDto(it) }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    fun findById(@PathVariable id: UUID): BaseSetResponseDto {
        val baseSet = baseSetService.findById(id)
        return baseSetMapper.toBaseSetResponseDto(baseSet)
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody requestDto: BaseSetRequestDto): BaseSetResponseDto {
        val baseSet = baseSetMapper.toBaseSet(requestDto)
        val savedBaseSet = baseSetService.save(baseSet)
        return baseSetMapper.toBaseSetResponseDto(savedBaseSet)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) {
        baseSetService.deleteById(id)
    }
}
