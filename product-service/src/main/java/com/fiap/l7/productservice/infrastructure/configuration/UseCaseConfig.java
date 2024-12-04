package com.fiap.l7.productservice.infrastructure.configuration;

import com.fiap.l7.productservice.domain.repository.ProductRepository;
import com.fiap.l7.productservice.domain.usecase.SaveProductUseCase;
import com.fiap.l7.productservice.domain.usecase.SearchProductsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public SaveProductUseCase saveProductUseCase(ProductRepository productRepository) {
        return new SaveProductUseCase(productRepository);
    }

    @Bean
    public SearchProductsUseCase searchProductsUseCase(ProductRepository productRepository) {
        return new SearchProductsUseCase(productRepository);
    }
}