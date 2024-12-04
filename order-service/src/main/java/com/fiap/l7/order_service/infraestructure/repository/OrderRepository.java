package com.fiap.l7.order_service.infraestructure.repository;

import com.fiap.l7.order_service.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}