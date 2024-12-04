package com.fiap.l7.logistic_service.domain.model;

public enum DeliveryStatus {
    PENDING,
    PICKED_UP,
    IN_TRANSIT,
    OUT_FOR_DELIVERY,
    DELIVERED,
    FAILED_ATTEMPT,
    RETURNED
}
