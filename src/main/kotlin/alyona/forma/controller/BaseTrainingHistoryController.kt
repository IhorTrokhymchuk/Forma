package alyona.forma.controller

import alyona.forma.dto.history.basetraining.BaseTrainingHistoryMinResponseDto
import alyona.forma.dto.history.basetraining.BaseTrainingHistoryResponseDto
import alyona.forma.mapper.BaseTrainingHistoryMapper
import alyona.forma.model.user.User
import alyona.forma.service.history.BaseTrainingHistoryService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/base-training-history")
class BaseTrainingHistoryController(
    private val baseTrainingHistoryService: BaseTrainingHistoryService,
    private val baseTrainingHistoryMapper: BaseTrainingHistoryMapper
) {

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    fun findAll(
        authentication: Authentication
    ): List<BaseTrainingHistoryMinResponseDto> {
        val user = authentication.principal as User
        return baseTrainingHistoryService.findAllByUser(user).map {
            baseTrainingHistoryMapper.toBaseTrainingHistoryMinResponseDto(it)
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    fun findById(@PathVariable id: UUID): BaseTrainingHistoryResponseDto {
        val baseTrainingHistory = baseTrainingHistoryService.findById(id)
        return baseTrainingHistoryMapper.toBaseTrainingHistoryResponseDto(baseTrainingHistory)
    }



}