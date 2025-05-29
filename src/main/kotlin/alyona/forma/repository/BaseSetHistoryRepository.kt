package alyona.forma.repository

import alyona.forma.model.history.BaseSetHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BaseSetHistoryRepository : JpaRepository<BaseSetHistory, UUID> {
}
