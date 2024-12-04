package com.fiap.l7.order_service.application.usecase;

import com.fiap.l7.order_service.domain.model.Order;
import com.fiap.l7.order_service.domain.model.OrderStatus;
import com.fiap.l7.order_service.infraestructure.config.OrderStreamConfig;
import com.fiap.l7.order_service.infraestructure.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Log4j2
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final OrderStreamConfig orderStreamConfig;

    @Value("message.payment.queue-name")
    private String queueName;

    @Autowired
    public CreateOrderUseCase(OrderRepository orderRepository, OrderStreamConfig orderStreamConfig) {
        this.orderRepository = orderRepository;
        this.orderStreamConfig = orderStreamConfig;
    }

    public Order execute(Order order) {
        order.setStatus(OrderStatus.CREATED);
        // Salvar pedido no banco de dados
        return orderRepository.save(order);
    }

}
