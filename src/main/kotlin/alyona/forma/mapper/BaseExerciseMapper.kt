package alyona.forma.mapper

import alyona.forma.dto.baseex.BaseExerciseRequestDto
import alyona.forma.dto.baseex.BaseExerciseResponseDto
import alyona.forma.exception.EntityNotFoundException
import alyona.forma.model.training.BaseExercise
import alyona.forma.model.trainingconst.MuscleGroup
import alyona.forma.repository.MuscleGroupRepository
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
)
abstract class BaseExerciseMapper {

    abstract fun toBaseExerciseResponseDto(baseExercise: BaseExercise): BaseExerciseResponseDto

    abstract fun toBaseExercise(requestDto: BaseExerciseRequestDto): BaseExercise
}