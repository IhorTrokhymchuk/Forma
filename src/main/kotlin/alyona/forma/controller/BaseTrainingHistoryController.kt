package alyona.forma.controller

import alyona.forma.dto.FromToDate
import alyona.forma.dto.history.basetraining.BaseTrainingHistoryMinResponseDto
import alyona.forma.dto.history.basetraining.BaseTrainingHistoryResponseDto
import alyona.forma.dto.statistic.StatusCountDto
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
import java.time.Instant
import java.time.LocalDate
import java.util.UUID

@RestController
@RequestMapping("/base-training-history")
class BaseTrainingHistoryController(
    private val baseTrainingHistoryService: BaseTrainingHistoryService,
    private val baseTrainingHistoryMapper: BaseTrainingHistoryMapper
) {

    @PatchMapping("/start/{id}")
    @PreAuthorize("hasAuthority('USER')")
    fun startTraining(
        @PathVariable id: UUID,
    ): BaseTrainingHistoryResponseDto {
        val baseTrainingHistory = baseTrainingHistoryService.startTraining(id)
        return baseTrainingHistoryMapper.toBaseTrainingHistoryResponseDto(baseTrainingHistory)
    }

    @PatchMapping("/finish/{id}")
    @PreAuthorize("hasAuthority('USER')")
    fun finishTraining(
        @PathVariable id: UUID,
    ): BaseTrainingHistoryResponseDto {
        val baseTrainingHistory = baseTrainingHistoryService.finishTraining(id)
        return baseTrainingHistoryMapper.toBaseTrainingHistoryResponseDto(baseTrainingHistory)
    }

    @GetMapping("/statistic/weight")
    @PreAuthorize("hasAuthority('USER')")
    fun getWeightStatistic(
        fromToDate: FromToDate,
        authentication: Authentication
    ): MutableMap<LocalDate, Long> {
        val user = authentication.principal as User
        return baseTrainingHistoryService.weightStatisticByUserAndDate(user, fromToDate.from, fromToDate.to)
    }

    @GetMapping("/statistic/statuses")
    @PreAuthorize("hasAuthority('USER')")
    fun getStatusesCount(
        fromToDate: FromToDate,
        authentication: Authentication
    ): List<StatusCountDto> {
        val user = authentication.principal as User
        return baseTrainingHistoryService.statisticByStatus(user, fromToDate.from, fromToDate.to)
    }
    @GetMapping("/last-completed")
    @PreAuthorize("hasAuthority('USER')")
    fun findLastCompleted(
        authentication: Authentication
    ): BaseTrainingHistoryResponseDto {
        val user = authentication.principal as User
        val baseTrainingHistory = baseTrainingHistoryService.findLastCompletedByUser(user)
        return baseTrainingHistoryMapper.toBaseTrainingHistoryResponseDto(baseTrainingHistory)
    }
    @GetMapping("/min/date")
    @PreAuthorize("hasAuthority('USER')")
    fun findAllByDate(
        fromToDate: FromToDate,
        authentication: Authentication
    ): List<BaseTrainingHistoryMinResponseDto> {
        val user = authentication.principal as User
        return baseTrainingHistoryService.findAllByUserAndFromTo(user , fromToDate.from, fromToDate.to).map {
            baseTrainingHistoryMapper.toBaseTrainingHistoryMinResponseDto(it)
        }
    }

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