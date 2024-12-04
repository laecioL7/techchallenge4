package com.fiap.l7.customer_service.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String street;
    private int number;
    private String complement;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    //pra definir o endereço prefencial do cliente
    private int priority;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;
}
