package com.fiap.l7.logistic_service.infraestructure.config;

import com.fiap.l7.logistic_service.application.usecase.ConsultDeliveryStatusUseCase;
import com.fiap.l7.logistic_service.application.usecase.GetUserAddressUseCase;
import com.fiap.l7.logistic_service.application.usecase.RequestDeliveryUseCase;
import com.fiap.l7.logistic_service.infraestructure.repository.DeliveryRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class UseCaseConfig {
    //TODO:

    private final WebClient.Builder loadBalancedWebClientBuilder;
    private final WebClient.Builder defaultWebClientBuilder;
    private final DeliveryRecordRepository deliveryRecordRepository;

    public UseCaseConfig(WebClient.Builder loadBalancedWebClientBuilder, WebClient.Builder defaultWebClientBuilder, DeliveryRecordRepository deliveryRecordRepository) {
        this.loadBalancedWebClientBuilder = loadBalancedWebClientBuilder;
        this.defaultWebClientBuilder = defaultWebClientBuilder;
        this.deliveryRecordRepository = deliveryRecordRepository;
    }

    @Bean
    public RequestDeliveryUseCase requestDeliveryUseCase() {
        return new RequestDeliveryUseCase(defaultWebClientBuilder, deliveryRecordRepository);
    }

    @Bean
    public ConsultDeliveryStatusUseCase consultDeliveryStatusUseCase() {
        return new ConsultDeliveryStatusUseCase(defaultWebClientBuilder, deliveryRecordRepository);
    }

    @Bean
    public GetUserAddressUseCase getUserAddressUseCase() {
        return new GetUserAddressUseCase(loadBalancedWebClientBuilder);
    }
}