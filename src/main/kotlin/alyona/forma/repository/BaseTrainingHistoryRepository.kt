package alyona.forma.repository

import alyona.forma.model.history.BaseTrainingHistory
import alyona.forma.model.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BaseTrainingHistoryRepository : JpaRepository<BaseTrainingHistory, UUID> {
    
    fun findByUser_Id(userId: UUID): List<BaseTrainingHistory>
}