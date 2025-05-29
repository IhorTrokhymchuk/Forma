package alyona.forma.repository

import alyona.forma.model.trainingconst.TrStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TrStatusRepository : JpaRepository<TrStatus, UUID> {

    fun findTrStatusByName(name: TrStatus.TrStatusName): TrStatus
}
