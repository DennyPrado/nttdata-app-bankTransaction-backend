package com.nttdata.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="movement")
public class Movements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movementId;

    @Column(name = "account_id", nullable = false)
    private Integer accountId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "movement_date", nullable = false)
    private Date movementDate;

    @Column(name = "type_of_movement", nullable = false, length = 50)
    private String typeOfMovement;

    @Column(name = "movement_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal movementValue;

    @Column(name = "balance", nullable = false, precision = 10, scale = 2)
    private BigDecimal balance;

    @Column(nullable = false, length = 1)
    private Boolean state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", foreignKey = @ForeignKey(name = "FK_movement_account"), insertable = false, updatable = false)
    private Accounts account;

    //-- Campos de auditoria
    @Column(name ="created_by_user", length = 30)
    private String createdByUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "last_modified_by_user", length = 30)
    private String lastModifiedByUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

}
