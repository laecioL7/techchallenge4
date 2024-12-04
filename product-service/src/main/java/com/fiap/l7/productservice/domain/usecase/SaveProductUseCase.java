package com.fiap.l7.productservice.domain.usecase;

import com.fiap.l7.productservice.domain.entity.Product;
import com.fiap.l7.productservice.domain.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

public class SaveProductUseCase {

    private final ProductRepository  productRepository;

    public SaveProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findById(long l) {
        return productRepository.findById(l);
    }

    public void saveAll(List<Product> products) {
        productRepository.saveAll(products);
    }

    public void save(Product product) {
        productRepository.save(product);
    }
}