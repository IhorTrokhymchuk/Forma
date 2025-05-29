package alyona.forma.repository

import alyona.forma.model.history.BaseExToPositionHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BaseExToPositionHistoryRepository : JpaRepository<BaseExToPositionHistory, UUID> {
}
