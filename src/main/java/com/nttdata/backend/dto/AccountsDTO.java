package com.nttdata.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class AccountsDTO {

    private Integer accountId;

    @NotNull(message = "Estimado usuario debe indicar el nùmero de cuenta")
    private Integer accountNumber;

    @NotEmpty(message = "Estimado usuario debe indicar el tipo de cuenta")
    @Size(max = 50, message = "Estimado usuario la longitud màxima del tipo de cuenta es 50 caràcteres")
    private String accountType;

    @NotNull(message = "Estimado usuario debe indicar el saldo inicial")
    private BigDecimal initialBalance;

    private Boolean state;

    @NotNull(message = "Estimado usuario debe indicar el id de la persona")
    private Integer personId;

    private String createdByUser;

    private Date createdDate;

    private String lastModifiedByUser;

    private Date lastModifiedDate;

}
