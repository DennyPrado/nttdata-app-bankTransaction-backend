package com.nttdata.backend.repository;

import com.nttdata.backend.dto.AccountStatusReportDTO;
import com.nttdata.backend.model.Movements;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IReportsRepo extends IGenericRepo<Movements, Integer> {

    @Query("select m FROM movement m where m.account.client.clientId = :clientId and m.movementDate between :startDate and :endDate")
    List<Movements> movementReportByUserAndDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("clientId") String clientId);
}
