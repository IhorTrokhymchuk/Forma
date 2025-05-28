package alyona.forma.mapper

import alyona.forma.dto.baseex.BaseExerciseRequestDto
import alyona.forma.dto.baseex.BaseExerciseResponseDto
import alyona.forma.exception.EntityNotFoundException
import alyona.forma.model.training.BaseExercise
import alyona.forma.model.training.BaseSet
import alyona.forma.model.trainingconst.MuscleGroup
import alyona.forma.repository.BaseSetRepository
import alyona.forma.repository.MuscleGroupRepository
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
        BaseSetMapper::class
    ]
)
abstract class BaseExerciseMapper {
    @Autowired
    protected lateinit var muscleGroupRepository: MuscleGroupRepository

    @Autowired
    protected lateinit var baseSetRepository: BaseSetRepository

    @Mapping(target = "muscleGroupName", source = "muscleGroup.name")
    abstract fun toBaseExerciseResponseDto(baseExercise: BaseExercise): BaseExerciseResponseDto

    @Mapping(target = "muscleGroup", source = "muscleGroupId", qualifiedByName = ["getMuscleGroupById"])
    @Mapping(target = "baseSets", source = "baseSetIds", qualifiedByName = ["getBaseSetsByIds"])
    abstract fun toBaseExercise(requestDto: BaseExerciseRequestDto): BaseExercise

    @Named("getMuscleGroupById")
    protected fun getMuscleGroupById(id: UUID): MuscleGroup = muscleGroupRepository.findById(id).orElseThrow {
        EntityNotFoundException("MuscleGroup not found with id: $id")
    }

    @Named("getBaseSetsByIds")
    protected fun getBaseSetsByIds(ids: List<UUID>):  MutableList<BaseSet> = baseSetRepository
        .findAllById(ids).toMutableList()
}