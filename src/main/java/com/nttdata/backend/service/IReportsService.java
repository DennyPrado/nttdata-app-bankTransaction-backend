package com.nttdata.backend.service;

import com.nttdata.backend.dto.AccountStatusReportDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IReportsService {

    List<AccountStatusReportDTO> movementReportByUserAndDate(LocalDateTime startDate, LocalDateTime endDate, String clientId) throws Exception;

}
