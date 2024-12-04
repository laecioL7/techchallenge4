//package com.fiap.l7.order_service.infraestructure.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQConfig {
//
//    public static final String ORDER_QUEUE = "orders-queue";
//    public static final String ORDER_EXCHANGE = "orders-exchange";
//
//    @Bean
//    public Queue orderQueue() {
//        return new Queue(ORDER_QUEUE, true);
//    }
//
//    @Bean
//    public DirectExchange orderExchange() {
//        return new DirectExchange(ORDER_EXCHANGE);
//    }
//
//    @Bean
//    public Binding binding(Queue orderQueue, DirectExchange orderExchange) {
//        return BindingBuilder.bind(orderQueue).to(orderExchange).with(ORDER_QUEUE);
//    }
//}
