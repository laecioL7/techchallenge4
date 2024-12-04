package com.fiap.l7.logistic_service.application.usecase.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private List<AddressDto> addresses;
}

