package com.nttdata.backend.dto;

import jakarta.validation.constraints.NotNull;
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
public class MovementsDTO {

    private Integer movementId;

    @NotNull(message = "Estimado usuario, debe indicar el id de la cuenta")
    private Integer accountId;

    private Date movementDate;

    private String typeOfMovement;

    @NotNull(message = "Estimado usuario, debe ingresar el valor del movimiento a realizar")
    private BigDecimal movementValue;

    private BigDecimal balance;

    private Boolean state;

    private String createdByUser;

    private Date createdDate;

    private String lastModifiedByUser;

    private Date lastModifiedDate;
}
