package alyona.forma.repository

import alyona.forma.model.training.BaseSet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BaseSetRepository : JpaRepository<BaseSet, UUID>
