package com.fiap.l7.order_service.infraestructure.adapters.dto;

import com.fiap.l7.order_service.domain.model.PaymentMethod;
import lombok.Data;

@Data
public class PaymentDto {
    private PaymentMethod paymentMethod;
    private OrderDto orderDto;
    private Double orderTotal;
}
