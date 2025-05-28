package alyona.forma.mapper

import alyona.forma.dto.user.RoleTypeResponseDto
import alyona.forma.model.user.RoleType
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
abstract class RoleTypeMapper {

    abstract fun toRoleTypeResponseDto(roleType: RoleType): RoleTypeResponseDto
}