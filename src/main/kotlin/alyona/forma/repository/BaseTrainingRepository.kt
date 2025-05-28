package alyona.forma.repository

import alyona.forma.model.training.BaseTraining
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BaseTrainingRepository : JpaRepository<BaseTraining, UUID> {
}
