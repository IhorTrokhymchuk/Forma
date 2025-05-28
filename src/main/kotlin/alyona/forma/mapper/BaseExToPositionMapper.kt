package alyona.forma.mapper

import alyona.forma.dto.baseextoposition.BaseExToPositionRequestDto
import alyona.forma.dto.baseextoposition.BaseExToPositionResponseDto
import alyona.forma.exception.EntityNotFoundException
import alyona.forma.model.training.BaseExToPosition
import alyona.forma.model.training.BaseExercise
import alyona.forma.model.training.BaseSet
import alyona.forma.repository.BaseExerciseRepository
import alyona.forma.repository.BaseSetRepository
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

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

    @Autowired
    private lateinit var baseSetRepository: BaseSetRepository

    @Mapping(target = "baseExercise", source = "baseExerciseId", qualifiedByName = ["getBaseExerciseById"])
    @Mapping(target = "baseSets", source = "baseSetIds", qualifiedByName = ["getBaseSetsByIds"])
    abstract fun toBaseExToPosition(baseExercise: BaseExToPositionRequestDto): BaseExToPosition

    abstract fun toBaseExToPositionResponseDto(baseExToPosition: BaseExToPosition): BaseExToPositionResponseDto

    @Named("getBaseExerciseById")
    fun getBaseExerciseById(baseExerciseId: UUID): BaseExercise = baseExerciseRepository.findById(baseExerciseId)
        .orElseThrow { EntityNotFoundException("BaseExercise not found with id: $baseExerciseId") }

    @Named("getBaseSetsByIds")
    protected fun getBaseSetsByIds(ids: List<UUID>):  MutableList<BaseSet> = baseSetRepository
        .findAllById(ids).toMutableList()
}
