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
@Entity(name="account")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "account_number", nullable = false, unique = true)
    private Integer accountNumber;

    @Column(name = "account_type", nullable = false, length = 50)
    private String accountType;

    @Column(name = "initial_balance", nullable = false, precision = 10, scale = 2)
    private BigDecimal initialBalance;

    @Column(nullable = false, length = 1)
    private Boolean state;

    @Column(name = "person_id", nullable = false)
    private Integer personId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", referencedColumnName = "person_id", foreignKey = @ForeignKey(name = "FK_account_client"), insertable = false, updatable = false)
    private ClientsView client;

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
