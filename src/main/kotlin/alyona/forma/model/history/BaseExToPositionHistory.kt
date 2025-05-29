package alyona.forma.model.history

import alyona.forma.model.training.BaseExercise
import alyona.forma.model.trainingconst.TrStatus
import alyona.forma.model.user.User
import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.util.*

@Entity
@Table(name = "base_ex_to_position_history")
class BaseExToPositionHistory {
    @Id
    @UuidGenerator
    val id: UUID? = null
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.DETACH])
    @JoinColumn(name = "base_ex_id")
    lateinit var baseExercise: BaseExercise
    var position: Long = 0
    @JoinTable(
        name = "base_ex_to_position_history_base_sets_history",
        joinColumns = [JoinColumn(name = "base_ex_to_position_history_id")],
        inverseJoinColumns = [JoinColumn(name = "base_set_history_id")]
    )
    @ManyToMany(cascade = [CascadeType.DETACH, CascadeType.PERSIST], fetch = FetchType.LAZY)
    var baseSets: MutableList<BaseSetHistory> = mutableListOf()
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.DETACH])
    @JoinColumn(name = "tr_status_id", nullable = false)
    lateinit var trStatus: TrStatus
}