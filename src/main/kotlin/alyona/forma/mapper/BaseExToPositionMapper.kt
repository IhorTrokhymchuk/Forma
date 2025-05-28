package alyona.forma.mapper

import alyona.forma.dto.baseex.BaseExerciseResponseDto
import alyona.forma.dto.baseextoposition.BaseExToPositionRequestDto
import alyona.forma.dto.baseextoposition.BaseExToPositionResponseDto
import alyona.forma.exception.EntityNotFoundException
import alyona.forma.model.training.BaseExToPosition
import alyona.forma.model.training.BaseExercise
import alyona.forma.repository.BaseExerciseRepository
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.springframework.beans.factory.annotation.Autowired
import java.util.UUID

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    uses = [
        BaseExerciseMapper::class
    ]
)
abstract class BaseExToPositionMapper {
    @Autowired
    private lateinit var baseExerciseRepository: BaseExerciseRepository

    @Mapping(target = "baseExercise", source = "baseExerciseId", qualifiedByName = ["getBaseExerciseById"])
    abstract fun toBaseExToPosition(baseExercise: BaseExToPositionRequestDto): BaseExToPosition

    abstract fun toBaseExToPositionResponseDto(baseExToPosition: BaseExToPosition): BaseExToPositionResponseDto

    @Named("getBaseExerciseById")
    fun getBaseExerciseById(baseExerciseId: UUID): BaseExercise = baseExerciseRepository.findById(baseExerciseId)
        .orElseThrow { EntityNotFoundException("BaseExercise not found with id: $baseExerciseId") }
}
