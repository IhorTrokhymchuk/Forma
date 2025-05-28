package alyona.forma.model.user

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import org.springframework.security.core.GrantedAuthority
import java.util.*

@Entity
@Table(name = "role_types")
class RoleType : GrantedAuthority {
    @Id
    @UuidGenerator
    val id: UUID? = null
    @Column(name = "name", nullable = false, unique = true, columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    lateinit var name: RoleName

    override fun getAuthority(): String {
        return name.name
    }

    enum class RoleName {
        USER,
        ADMIN;
    }
}