package alyona.forma.mapper

import alyona.forma.dto.history.baseextoposition.BaseExToPositionHistoryResponseDto
import alyona.forma.model.history.BaseExToPositionHistory
import alyona.forma.model.training.BaseExToPosition
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    uses = [
        BaseExerciseMapper::class,
        BaseSetHistoryMapper::class
    ]
)
abstract class BaseExToPositionHistoryMapper {

    @Mapping(target = "trStatusDisplayName", source = "trStatus.displayName")
    abstract fun toBaseExToPositionResponseDto(baseExToPositionHistory: BaseExToPositionHistory): BaseExToPositionHistoryResponseDto

}
