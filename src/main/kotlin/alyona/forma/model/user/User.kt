package alyona.forma.model.user

import alyona.forma.model.training.BaseTraining
import alyona.forma.model.trainingconst.TrainingLevel
import alyona.forma.model.usertrainingplan.TrainingPlan
import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import org.springframework.security.core.userdetails.UserDetails
import java.time.Instant
import java.util.*

@Entity
@Table(name = "users")
class User : UserDetails {
    @Id
    @UuidGenerator
    val id: UUID? = null
    @Column(name = "email", nullable = false, unique = true)
    var email: String = ""
    @Column(name = "password", nullable = false)
    var userPassword: String = ""
    @Column(name = "first_name", nullable = false)
    var firstName: String = ""
    @Column(name = "last_name", nullable = false)
    var lastName: String = ""
    @ManyToMany
    @JoinTable(
        name = "users_role_types",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_type_id")]
    )
    var roles: MutableSet<RoleType> = mutableSetOf()
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.DETACH, CascadeType.PERSIST])
    @JoinColumn(name = "training_level_id", nullable = false)
    lateinit var trainingLevel: TrainingLevel
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.DETACH, CascadeType.PERSIST])
    @JoinColumn(name = "base_training_id", nullable = false)
    lateinit var baseTraining: BaseTraining
    var age: Long = -1
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar")
    lateinit var gender: Gender
    var weight: Long = -1
    var height: Long = -1
    @Column(name = "last_login", nullable = true)
    var lastLogin: Instant = Instant.now()
    @Column(name = "days_per_week", nullable = false)
    var daysPerWeek: Long = 2

    enum class Gender {
        MALE,
        FEMALE
    }

    override fun getAuthorities(): MutableCollection<RoleType> {
        return this.roles
    }

    override fun getPassword(): String {
        return this.userPassword
    }

    override fun getUsername(): String {
        return this.email
    }
}