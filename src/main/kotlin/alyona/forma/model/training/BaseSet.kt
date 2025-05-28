package alyona.forma.model.training

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.UuidGenerator
import java.util.UUID

@Entity
@Table(name = "base_sets")
class BaseSet {
    @Id
    @UuidGenerator
    val id: UUID? = null
    @Column(nullable = false)
    var kg: Long = 0
    @Column(nullable = false)
    var reps: Long = 0
    @Column(nullable = false)
    var position : Long = 0
}