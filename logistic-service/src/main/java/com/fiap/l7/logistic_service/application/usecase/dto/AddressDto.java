package com.fiap.l7.logistic_service.application.usecase.dto;

import lombok.Data;

@Data
public class AddressDto {
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
}
