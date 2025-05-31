package alyona.forma.controller

import alyona.forma.dto.baseextoposition.BaseExToPositionResponseDto
import alyona.forma.dto.history.baseextoposition.BaseExToPositionHistoryResponseDto
import alyona.forma.dto.history.baseset.BaseSetHistoryRequestDto
import alyona.forma.mapper.BaseExToPositionHistoryMapper
import alyona.forma.model.history.BaseSetHistory
import alyona.forma.service.history.BaseExToPositionHistoryService
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/base-ex-to-position-history")
class BaseExToPositionHistoryController(
    private val baseExToPositionHistoryService: BaseExToPositionHistoryService,
    private val baseExToPositionHistoryMapper: BaseExToPositionHistoryMapper
) {

    @PatchMapping("/finish/{id}")
    @PreAuthorize("hasAuthority('USER')")
    fun patch(
        @PathVariable id: UUID,
        @RequestBody baseSetHistories: List<BaseSetHistoryRequestDto>,
    ): BaseExToPositionHistoryResponseDto {
        val completeExToPosition = baseExToPositionHistoryService.completeExToPosition(id, baseSetHistories)
        val response = baseExToPositionHistoryMapper.toBaseExToPositionResponseDto(completeExToPosition)
        return response
    }
}