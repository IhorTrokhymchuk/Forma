package alyona.forma.mapper

import alyona.forma.dto.history.basetraining.BaseTrainingHistoryResponseDto
import alyona.forma.model.history.BaseTrainingHistory
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    uses = [
        BaseExToPositionHistoryMapper::class
    ]
)
abstract class BaseTrainingHistoryMapper {

    @Mapping(target = "trStatusDisplayName", source = "trStatus.displayName")
    abstract fun toBaseTrainingHistoryResponseDto(baseTrainingHistory: BaseTrainingHistory): BaseTrainingHistoryResponseDto
}