package alyona.forma.mapper

import alyona.forma.dto.basetraining.BaseTrainingRequestDto
import alyona.forma.dto.basetraining.BaseTrainingResponseDto
import alyona.forma.exception.EntityNotFoundException
import alyona.forma.model.TrainingLevel
import alyona.forma.model.training.BaseExToPosition
import alyona.forma.model.training.BaseTraining
import alyona.forma.repository.BaseExToPositionRepository
import alyona.forma.repository.TrainingLevelRepository
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
        BaseExToPositionMapper::class
    ]
)
abstract class BaseTrainingMapper {
    @Autowired
    private lateinit var baseExToPositionRepository: BaseExToPositionRepository

    @Autowired
    private lateinit var trainingLevelRepository: TrainingLevelRepository

    @Mapping(target = "trainingLevelDisplayName", source = "trainingLevel.displayName")
    abstract fun toBaseTrainingResponseDto(baseTraining: BaseTraining): BaseTrainingResponseDto

    @Mapping(target = "trainingLevel", source = "trainingLevelId", qualifiedByName = ["getTrainingLevelById"])
    @Mapping(target = "baseExToPositions", source = "baseExToPositionIds", qualifiedByName = ["getBaseExToPositionById"])
    abstract fun toBaseTraining(requestDto: BaseTrainingRequestDto): BaseTraining

    @Named("getTrainingLevelById")
    fun getTrainingLevelById(trainingLevelId: UUID): TrainingLevel = trainingLevelRepository.findById(trainingLevelId)
        .orElseThrow { EntityNotFoundException("Training level not found with id: $trainingLevelId") }

    @Named("getBaseExToPositionById")
    fun getBaseExToPositionById(baseExToPositionIds: List<UUID>): MutableList<BaseExToPosition> = baseExToPositionRepository.findAllById(baseExToPositionIds)
        .toMutableList()
}