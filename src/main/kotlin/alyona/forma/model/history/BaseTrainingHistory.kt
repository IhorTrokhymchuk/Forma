package alyona.forma.model.history

import alyona.forma.model.trainingconst.TrStatus
import alyona.forma.model.trainingconst.TrainingLevel
import alyona.forma.model.user.User
import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.time.Instant
import java.util.*

@Entity
@Table(name = "base_trainings_history")
class BaseTrainingHistory {
    @Id
    @UuidGenerator
    val id: UUID? = null
    @Column(nullable = false)
    var name: String = ""
    @JoinTable(
        name = "base_trainings_history_base_ex_to_position",
        joinColumns = [JoinColumn(name = "base_training_id")],
        inverseJoinColumns = [JoinColumn(name = "base_ex_to_position_history_id")]
    )
    @ManyToMany(cascade = [CascadeType.DETACH, CascadeType.PERSIST], fetch = FetchType.LAZY)
    var baseExToPositions: MutableList<BaseExToPositionHistory> = mutableListOf()
    @Column(columnDefinition = "TEXT")
    var description: String? = null
    @Column(name = "date_time", nullable = false)
    var dateTime: Instant = Instant.now()
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.DETACH])
    @JoinColumn(name = "user_id", nullable = false)
    lateinit var user: User
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.DETACH])
    @JoinColumn(name = "tr_status_id", nullable = false)
    lateinit var trStatus: TrStatus
}