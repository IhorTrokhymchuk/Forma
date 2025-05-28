package alyona.forma.model.training

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.util.*

@Entity
@Table(name = "base_ex_to_position")
class BaseExToPosition {
    @Id
    @UuidGenerator
    val id: UUID? = null
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.DETACH])
    @JoinColumn(name = "base_ex_id")
    lateinit var baseExercise: BaseExercise
    var position: Long = 0

    @JoinTable(
        name = "base_ex_to_position_base_sets",
        joinColumns = [JoinColumn(name = "base_ex_to_position_id")],
        inverseJoinColumns = [JoinColumn(name = "base_set_id")]
    )
    @ManyToMany(cascade = [CascadeType.DETACH, CascadeType.PERSIST], fetch = FetchType.LAZY)
    var baseSets: MutableList<BaseSet> = mutableListOf()

}