package com.fiap.l7.logistic_service.application.usecase;

import com.fiap.l7.logistic_service.application.usecase.dto.CustomerDto;
import com.fiap.l7.logistic_service.domain.model.Order;
import org.springframework.web.reactive.function.client.WebClient;

public class GetUserAddressUseCase {

    private final WebClient.Builder loadBalancedWebClientBuilder;

    public GetUserAddressUseCase(WebClient.Builder loadBalancedWebClientBuilder) {
        this.loadBalancedWebClientBuilder = loadBalancedWebClientBuilder;
    }

    public CustomerDto execute(Order order) {
        return loadBalancedWebClientBuilder.build()
                .get()
                .uri("http://customer-service/customers/{id}", order.getCustomerId())
                .retrieve()
                .bodyToMono(CustomerDto.class)
                .block();
    }

}
