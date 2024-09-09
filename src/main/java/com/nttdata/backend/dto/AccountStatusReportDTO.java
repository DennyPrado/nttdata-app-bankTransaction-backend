package com.nttdata.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AccountStatusReportDTO {

    private Date        movementDate;
    private String      clientName;
    private Integer     accountNumber;
    private String      accountType;
    private BigDecimal  initialBalance;
    private Boolean     movementStatus;
    private BigDecimal  movementValue;
    private BigDecimal  availableBalance;

}
