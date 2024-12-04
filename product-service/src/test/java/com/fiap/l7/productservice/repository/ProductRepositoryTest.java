package com.fiap.l7.productservice.repository;

import com.fiap.l7.productservice.domain.entity.Product;
import com.fiap.l7.productservice.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testSaveProduct() {
        Product product = new Product();
        product.setName("Sample Product");
        productRepository.save(product);
        assertTrue(productRepository.findById(product.getId()).isPresent());
    }
}
