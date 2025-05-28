package alyona.forma.model.training

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.UuidGenerator
import java.util.UUID

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
}