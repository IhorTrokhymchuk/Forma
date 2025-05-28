package alyona.forma.repository

import alyona.forma.model.user.RoleType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleTypeRepository : JpaRepository<RoleType, UUID> {
    fun getRoleTypesByName(name: RoleType.RoleName): RoleType
}