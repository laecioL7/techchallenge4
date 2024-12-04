package com.fiap.l7.logistic_service.infraestructure.gateway;

import com.fiap.l7.logistic_service.application.usecase.dto.AddressDto;
import com.fiap.l7.logistic_service.application.usecase.dto.CustomerDto;
import lombok.Data;

@Data
public class DeliveryRequest {
    private String name;
    private String email;
    private String phone;
    private String street;
    private int number;
    private String complement;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
