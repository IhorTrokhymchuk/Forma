package alyona.forma.mapper

import alyona.forma.dto.basetraining.BaseTrainingMinResponseDto
import alyona.forma.dto.basetraining.BaseTrainingRequestDto
import alyona.forma.dto.basetraining.BaseTrainingResponseDto
import alyona.forma.exception.EntityNotFoundException
import alyona.forma.model.trainingconst.TrainingLevel
import alyona.forma.model.training.BaseExToPosition
import alyona.forma.model.training.BaseTraining
import alyona.forma.repository.BaseExToPositionRepository
import alyona.forma.repository.TrainingLevelRepository
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
        BaseExToPositionMapper::class
    ]
)
abstract class BaseTrainingMapper {
    @Autowired
    private lateinit var baseExToPositionRepository: BaseExToPositionRepository

    abstract fun toBaseTrainingResponseDto(baseTraining: BaseTraining): BaseTrainingResponseDto

    abstract fun toBaseTrainingMinResponseDto(baseTraining: BaseTraining): BaseTrainingMinResponseDto

    @Mapping(target = "baseExToPositions", source = "baseExToPositionIds", qualifiedByName = ["getBaseExToPositionById"])
    abstract fun toBaseTraining(requestDto: BaseTrainingRequestDto): BaseTraining

    @Named("getBaseExToPositionById")
    fun getBaseExToPositionById(baseExToPositionIds: List<UUID>): MutableList<BaseExToPosition> = baseExToPositionRepository.findAllById(baseExToPositionIds)
        .toMutableList()
}
