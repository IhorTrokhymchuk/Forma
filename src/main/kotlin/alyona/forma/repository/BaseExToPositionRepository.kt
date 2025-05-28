package alyona.forma.repository

import alyona.forma.model.training.BaseExToPosition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BaseExToPositionRepository : JpaRepository<BaseExToPosition, UUID> {
}
