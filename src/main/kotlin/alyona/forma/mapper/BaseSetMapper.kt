package alyona.forma.mapper

import alyona.forma.dto.baseset.BaseSetRequestDto
import alyona.forma.dto.baseset.BaseSetResponseDto
import alyona.forma.model.training.BaseSet
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
abstract class BaseSetMapper {

    abstract fun toBaseSetResponseDto(baseSet: BaseSet): BaseSetResponseDto

    abstract fun toBaseSet(requestDto: BaseSetRequestDto): BaseSet
}