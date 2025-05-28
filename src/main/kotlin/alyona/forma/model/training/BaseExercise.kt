package alyona.forma.model.training

import alyona.forma.model.trainingconst.MuscleGroup
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
@Table(name = "base_exercises")
class BaseExercise {
    @Id
    @UuidGenerator
    val id: UUID? = null
    @Column(nullable = false, unique = true)
    var name: String = ""
    @Column(columnDefinition = "TEXT")
    var description: String? = null
    @ManyToOne(cascade = [CascadeType.DETACH, CascadeType.PERSIST], fetch = FetchType.LAZY)
    @JoinColumn(name = "muscle_group_id")
    lateinit var muscleGroup: MuscleGroup
    @Column(nullable = false)
    var videoUrl: String = ""
    @Column(columnDefinition = "TEXT")
    var mainImage: String? = null
    @Column(columnDefinition = "TEXT")
    var miniImage: String? = null

}