package alyona.forma.mapper

import alyona.forma.dto.history.baseset.BaseSetHistoryResponseDto
import alyona.forma.model.history.BaseSetHistory
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
abstract class BaseSetHistoryMapper {

    abstract fun toBaseSetHistoryResponseDto(baseSetHistory: BaseSetHistory): BaseSetHistoryResponseDto

}