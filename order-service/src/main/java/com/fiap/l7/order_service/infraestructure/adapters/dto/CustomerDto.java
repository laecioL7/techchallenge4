package com.fiap.l7.order_service.infraestructure.adapters.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
}

