package com.fiap.l7.logistic_service.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order implements Serializable {
    private Long id;
    private Long customerId;
    private OrderStatus status;
    private List<OrderItem> items;
}
