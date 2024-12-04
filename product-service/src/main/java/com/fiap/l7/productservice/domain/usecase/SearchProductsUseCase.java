package com.fiap.l7.productservice.domain.usecase;

import com.fiap.l7.productservice.domain.entity.Product;
import com.fiap.l7.productservice.domain.exceptions.NoResultsFoundException;
import com.fiap.l7.productservice.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class SearchProductsUseCase {

    private final ProductRepository productRepository;

    public SearchProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> searchProducts() {
        List<Product> ProductList = productRepository.findAll();

        //se for vazio retornar exception
        if(ProductList.isEmpty()){
            throw new NoResultsFoundException("Nenhum Produto encontrado");
        }else {
            return ProductList;
        }
    }

    public Product searchOne(Long productId){
        Optional<Product> product = productRepository.findById(productId);
        //se for vazio retornar exception
        if(!product.isPresent()){
            throw new NoResultsFoundException("Nenhum Produto encontrado com o id informado");
        }else {
            return product.get();
        }
    }
}
