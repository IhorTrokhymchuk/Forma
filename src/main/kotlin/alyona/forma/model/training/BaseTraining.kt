package alyona.forma.model.training

import alyona.forma.model.trainingconst.TrainingLevel
import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.util.*

@Entity
@Table(name = "base_trainings")
class BaseTraining {
    @Id
    @UuidGenerator
    val id: UUID? = null
    @Column(nullable = false, unique = true)
    var name: String = ""
    @JoinTable(
        name = "base_training_base_ex_to_position",
        joinColumns = [JoinColumn(name = "base_training_id")],
        inverseJoinColumns = [JoinColumn(name = "base_ex_to_position")]
    )
    @ManyToMany(cascade = [CascadeType.DETACH, CascadeType.PERSIST], fetch = FetchType.LAZY)
    var baseExToPositions: MutableList<BaseExToPosition> = mutableListOf()
    @Column(columnDefinition = "TEXT")
    var description: String? = null
}