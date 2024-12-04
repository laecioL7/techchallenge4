package com.fiap.l7.order_service.infraestructure.config;

import com.fiap.l7.order_service.application.usecase.CreateOrderUseCase;
import com.fiap.l7.order_service.infraestructure.repository.OrderRepository;
import com.fiap.l7.order_service.infraestructure.adapters.ProductClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    private final OrderRepository orderRepository;
    private final OrderStreamConfig orderStreamConfig;

    // Injeção de dependências no construtor
    public UseCaseConfig(OrderRepository orderRepository, ProductClient productClient, OrderStreamConfig orderStreamConfig) {
        this.orderRepository = orderRepository;
        this.orderStreamConfig = orderStreamConfig;
    }

    @Bean
    public CreateOrderUseCase createOrderUseCase() {
        return new CreateOrderUseCase(orderRepository,orderStreamConfig);
    }

}