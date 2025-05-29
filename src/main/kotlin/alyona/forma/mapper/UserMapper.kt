package alyona.forma.mapper

import alyona.forma.dto.user.UserResponseDto
import alyona.forma.model.user.User
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    uses = [RoleTypeMapper::class]
)
abstract class UserMapper {


    @Mapping(target = "baseTrainingName", source = "baseTraining.name")
    @Mapping(target = "trainingLevelDisplayName", source = "trainingLevel.displayName")
    abstract fun toUserResponseDto(user: User): UserResponseDto
}