package com.fiap.l7.logistic_service.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ExternalWebClientConfig {

    @Bean
    public WebClient.Builder defaultWebClientBuilder() {
        return WebClient.builder();
    }
}
