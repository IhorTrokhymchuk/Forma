package alyona.forma.model.usertrainingplan

import alyona.forma.model.user.User
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.UuidGenerator
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "training_plans")
class TrainingPlan {
    @Id
    @UuidGenerator
    val id: UUID? = null
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.DETACH])
    @JoinColumn(name = "user_id")
    lateinit var user: User
    @JoinColumn(nullable = false, name = "date_time")
    var dateTime: Instant = Instant.now()
    
}