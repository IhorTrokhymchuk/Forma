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
    @Autowired
    protected lateinit var muscleGroupRepository: MuscleGroupRepository

    @Mapping(target = "muscleGroupName", source = "muscleGroup.name")
    abstract fun toBaseExerciseResponseDto(baseExercise: BaseExercise): BaseExerciseResponseDto

    @Mapping(target = "muscleGroup", source = "muscleGroupId", qualifiedByName = ["getMuscleGroupById"])
    abstract fun toBaseExercise(requestDto: BaseExerciseRequestDto): BaseExercise

    @Named("getMuscleGroupById")
    protected fun getMuscleGroupById(id: UUID): MuscleGroup = muscleGroupRepository.findById(id).orElseThrow {
        EntityNotFoundException("MuscleGroup not found with id: $id")
    }

}