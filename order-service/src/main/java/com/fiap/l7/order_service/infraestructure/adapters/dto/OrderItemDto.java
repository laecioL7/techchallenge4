package com.fiap.l7.order_service.infraestructure.adapters.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long productId;
    private int quantity;
}
