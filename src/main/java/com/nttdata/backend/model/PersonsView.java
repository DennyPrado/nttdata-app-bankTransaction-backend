package com.nttdata.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Data
@Immutable
@Entity(name = "person")
public class PersonsView {

    @Id
    @Column(name = "person_id")
    private Integer personId;

    private String name;

    private String gender;

    private Integer age;

    private String dni;

    private String address;

    private String phone;

}
