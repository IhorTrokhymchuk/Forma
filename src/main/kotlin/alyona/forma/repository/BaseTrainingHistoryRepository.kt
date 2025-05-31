package alyona.forma.repository

import alyona.forma.dto.statistic.StatusCountDto
import alyona.forma.model.history.BaseTrainingHistory
import alyona.forma.model.trainingconst.TrStatus
import alyona.forma.model.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
interface BaseTrainingHistoryRepository : JpaRepository<BaseTrainingHistory, UUID> {
    
    fun findByUser_Id(userId: UUID): List<BaseTrainingHistory>

    @Query("SELECT bth FROM BaseTrainingHistory bth WHERE bth.user.id = :userId AND bth.dateTime BETWEEN :from AND :to")
    fun findByUserIdAndDates(userId: UUID, from: LocalDate, to: LocalDate): List<BaseTrainingHistory>

    @Query("SELECT bth FROM BaseTrainingHistory bth WHERE bth.user.id = :userId AND bth.trStatus.name = :statusName AND bth.dateTime BETWEEN :from AND :to")
    fun findByUserIdAndDatesAndStatusName(userId: UUID, from: LocalDate, to: LocalDate, statusName: TrStatus.TrStatusName): List<BaseTrainingHistory>

    @Query("SELECT bth FROM BaseTrainingHistory bth WHERE bth.user.id = :userId AND bth.trStatus.name = :statusName ORDER BY bth.dateTime DESC LIMIT 1")
    fun findLastByUserIdAndTrStatusName(userId: UUID, statusName: TrStatus.TrStatusName): Optional<BaseTrainingHistory>

    @Query(
        """
    SELECT bth.trStatus.displayName AS statusName, COUNT(bth) AS count
    FROM BaseTrainingHistory bth
    WHERE bth.user.id = :userId AND bth.dateTime BETWEEN :from AND :to
    GROUP BY bth.trStatus.displayName
    """
    )
    fun countByStatusDisplayNameBetweenDates(
        @Param("userId") userId: UUID,
        @Param("from") from: LocalDate,
        @Param("to") to: LocalDate
    ): List<StatusCountDto>

    fun findAllByDateTimeAndTrStatus_Name(date: LocalDate, trStatusName: TrStatus.TrStatusName): List<BaseTrainingHistory>

}