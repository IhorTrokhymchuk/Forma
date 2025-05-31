package alyona.forma.controller

import alyona.forma.dto.history.basetraining.BaseTrainingHistoryResponseDto
import alyona.forma.mapper.BaseTrainingHistoryMapper
import alyona.forma.model.user.User
import alyona.forma.service.history.BaseTrainingHistoryService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
    ): List<BaseTrainingHistoryResponseDto> {
        val user = authentication.principal as User
        return baseTrainingHistoryService.findAllByUser(user).map {
            baseTrainingHistoryMapper.toBaseTrainingHistoryResponseDto(it)
        }
    }


}