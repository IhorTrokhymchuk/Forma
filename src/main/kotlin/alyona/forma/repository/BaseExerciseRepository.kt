package alyona.forma.repository

import alyona.forma.model.training.BaseExercise
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BaseExerciseRepository : JpaRepository<BaseExercise, UUID> {
    fun existsBaseExerciseByName(name: String): Boolean
}
