package com.nttdata.backend.service.impl;

import com.nttdata.backend.dto.AccountStatusReportDTO;
import com.nttdata.backend.exception.ModeloNotFoundException;
import com.nttdata.backend.model.Movements;
import com.nttdata.backend.repository.IReportsRepo;
import com.nttdata.backend.service.IReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportsServiceImpl implements IReportsService {

    private final IReportsRepo reportsRepo;

    @Override
    public List<AccountStatusReportDTO> movementReportByUserAndDate(LocalDateTime startDate, LocalDateTime endDate, String clientId) throws Exception {
        try{
            List<Movements> movementVo = reportsRepo.movementReportByUserAndDate(getDateTransform(startDate), getDateTransform(endDate), clientId);
            List<AccountStatusReportDTO> reportVos = new ArrayList<>();
            if (!movementVo.isEmpty()) {
                movementVo.forEach( data -> {
                    AccountStatusReportDTO movement = AccountStatusReportDTO.builder()
                            .movementDate(data.getMovementDate())
                            .clientName(data.getAccount().getClient().getName())
                            .accountNumber(data.getAccount().getAccountNumber())
                            .accountType(data.getAccount().getAccountType())
                            .initialBalance(data.getAccount().getInitialBalance())
                            .movementStatus(data.getState())
                            .movementValue(data.getMovementValue())
                            .availableBalance(data.getBalance())
                            .build();
                    reportVos.add(movement);
                });
            }
            return reportVos;
        }catch (Exception e){
            throw new ModeloNotFoundException("Ocurrió un error en el proceso movementReportByUserAndDate: {} " + e.getMessage());
        }
    }

    private Date getDateTransform (LocalDateTime localDateTime ){
        // Convertir a ZonedDateTime
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

        // Convertir a Instant
        Instant instant = zonedDateTime.toInstant();

        // Crear Date
        return Date.from(instant);
    }
}
