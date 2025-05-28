package alyona.forma.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.UuidGenerator
import java.util.UUID

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