package com.fiap.l7.productservice.infrastructure.batch;

import com.fiap.l7.productservice.domain.entity.Product;
import org.springframework.batch.item.ItemProcessor;

public class ProductProcessor implements ItemProcessor<Product, Product> {

    @Override
    public Product process(Product product) throws Exception {
        return product;
    }
}
