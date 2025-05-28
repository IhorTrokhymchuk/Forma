package alyona.forma.model.trainingconst

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.util.*

@Entity
@Table(name = "muscle_groups")
class MuscleGroup {
    @Id
    @UuidGenerator
    val id: UUID? = null
    @Column(nullable = false, unique = true)
    var name: String = ""
}