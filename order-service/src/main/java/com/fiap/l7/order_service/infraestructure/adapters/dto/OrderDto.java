package com.fiap.l7.order_service.infraestructure.adapters.dto;

import com.fiap.l7.order_service.domain.model.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long customerId;
    private List<OrderItem> items;
}
