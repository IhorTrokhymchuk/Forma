package alyona.forma.model.training

import alyona.forma.model.TrainingLevel
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.UuidGenerator
import java.util.UUID

@Entity
@Table(name = "base_trainings")
class BaseTraining {
    @Id
    @UuidGenerator
    val id: UUID? = null
    @Column(nullable = false, unique = true)
    var name: String = ""
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.DETACH])
    @JoinColumn(name = "training_level_id")
    lateinit var trainingLevel: TrainingLevel
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