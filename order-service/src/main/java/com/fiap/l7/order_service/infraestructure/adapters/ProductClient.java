package com.fiap.l7.order_service.infraestructure.adapters;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ProductClient {

    private final WebClient.Builder webClientBuilder;

    public ProductClient(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Integer checkStockAvailability(Long productId) {
        return webClientBuilder.build()
                .get()
                .uri("http://product-service/get-one/{productId}", productId)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
    }

}