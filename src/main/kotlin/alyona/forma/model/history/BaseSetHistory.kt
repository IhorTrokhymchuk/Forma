package alyona.forma.model.history

import alyona.forma.model.trainingconst.TrStatus
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.UuidGenerator
import java.util.*

@Entity
@Table(name = "base_sets_history")
class BaseSetHistory {
    @Id
    @UuidGenerator
    val id: UUID? = null
    @Column(nullable = false)
    var kg: Long = 0
    @Column(nullable = false)
    var reps: Long = 0
    @Column(nullable = false)
    var position : Long = 0
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.DETACH])
    @JoinColumn(name = "tr_status_id", nullable = false)
    lateinit var trStatus: TrStatus
}