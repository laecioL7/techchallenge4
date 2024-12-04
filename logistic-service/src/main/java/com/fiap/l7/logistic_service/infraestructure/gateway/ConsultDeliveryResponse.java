package com.fiap.l7.logistic_service.infraestructure.gateway;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.l7.logistic_service.domain.model.DeliveryStatus;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ConsultDeliveryResponse {
    @JsonProperty("delivery_status")
    private DeliveryStatus deliveryStatus;

    @JsonProperty("estimated_delivery_time")
    private ZonedDateTime estimatedDeliveryTime;

    @JsonProperty("actual_delivery_time")
    private ZonedDateTime actualDeliveryTime;

    @JsonProperty("delivery_agent")
    private String deliveryAgent;

    @JsonProperty("delivery_signature")
    private String deliverySignature;

    @JsonProperty("tracking_number")
    private String trackingNumber;

    @JsonProperty("delivery_notes")
    private String deliveryNotes;
}
