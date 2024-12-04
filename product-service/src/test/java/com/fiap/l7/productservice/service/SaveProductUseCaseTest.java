
package com.fiap.l7.productservice.service;

import com.fiap.l7.productservice.domain.usecase.SaveProductUseCase;
import com.fiap.l7.productservice.domain.entity.Product;
import com.fiap.l7.productservice.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveProductUseCaseTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SaveProductUseCase saveProductUseCase;

    @Test
    void testFindById() {
        Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Optional<Product> foundProduct = saveProductUseCase.findById(1L);
        assertTrue(foundProduct.isPresent());
    }
}
