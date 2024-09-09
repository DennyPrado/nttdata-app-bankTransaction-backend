package com.nttdata.backend.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;

@EqualsAndHashCode(callSuper = true)
@Data
//@Immutable
@Entity(name = "client")
public class ClientsView extends PersonsView{

    private String clientId;

    private String password;

    private Boolean state;

}
