package com.fiap.l7.order_service.domain.model;

import lombok.Data;

@Data
public class Payment {
    private Long orderId;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private String protocol;
}
