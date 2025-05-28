package alyona.forma.repository

import alyona.forma.model.trainingconst.MuscleGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface MuscleGroupRepository : JpaRepository<MuscleGroup, UUID> {

}