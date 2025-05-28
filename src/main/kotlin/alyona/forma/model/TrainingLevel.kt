package alyona.forma.model

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.util.*

@Entity
@Table(name = "training_levels")
class TrainingLevel {
    @Id
    @UuidGenerator
    val id: UUID? = null
    @Column(name = "display_name", nullable = false, unique = true)
    val displayName: String = ""
    @Column(name = "name", nullable = false, unique = true, columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    lateinit var name: TrainingLevelName

    enum class TrainingLevelName {
        BEGINNER,
        INTERMEDIATE,
        ADVANCED
    }
}