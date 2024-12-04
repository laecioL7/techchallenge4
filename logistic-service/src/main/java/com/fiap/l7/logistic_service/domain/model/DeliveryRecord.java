package com.fiap.l7.logistic_service.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class DeliveryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private DeliveryStatus deliveryStatus;
    private Long addressId;
    private String trackingCode;
    // e.g., "PENDING", "IN_PROGRESS", "DELIVERED"
}