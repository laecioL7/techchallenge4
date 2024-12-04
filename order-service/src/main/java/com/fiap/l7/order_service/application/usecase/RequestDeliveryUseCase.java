package com.fiap.l7.order_service.application.usecase;

import com.fiap.l7.order_service.domain.model.Order;
import com.fiap.l7.order_service.infraestructure.config.OrderStreamConfig;
import com.fiap.l7.order_service.infraestructure.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;

@Log4j2
public class RequestDeliveryUseCase {

    private final OrderRepository orderRepository;
    private final OrderStreamConfig orderStreamConfig;

    @Value("message.order.queue-name")
    private String queueName;

    public RequestDeliveryUseCase(OrderRepository orderRepository, OrderStreamConfig orderStreamConfig) {
        this.orderRepository = orderRepository;
        this.orderStreamConfig = orderStreamConfig;
    }

    public void execute(Order order) {
        log.info("publicando pedido para entrega");
        // Publicar mensagem no RabbitMQ indicando que o pedido foi criado e pago
        orderStreamConfig.sendOrder(order,queueName);
    }

}
