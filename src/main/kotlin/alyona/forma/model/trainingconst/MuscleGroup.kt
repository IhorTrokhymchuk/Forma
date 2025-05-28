package alyona.forma.model.trainingconst

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
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