package com.fiap.l7.order_service.infraestructure.config;

import com.fiap.l7.order_service.domain.model.Order;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;


@Log4j2
@Component
public class OrderStreamConfig {

    private final BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    @Bean
    public Supplier<Order> orderSupplier() {
        return orderQueue::poll;
    }

    public void sendOrder(Order order, String queueName) {
        log.info("Pedido {} enviado para fila {}: ", order.getId(), queueName);
        // Logic to send the order to the specified queue
        orderQueue.offer(order);
    }

    //Com amqp
//    private final RabbitTemplate rabbitTemplate;
//
//    @Inject
//    public OrderStreamConfig(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    public void publishOrder(Order order) {
//        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_EXCHANGE, RabbitMQConfig.ORDER_QUEUE, order);
//        log.info("Order enviado para o RabbitMQ: " + order);
//    }
}
