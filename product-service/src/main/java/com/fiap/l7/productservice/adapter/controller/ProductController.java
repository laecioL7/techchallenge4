package com.fiap.l7.productservice.adapter.controller;

import com.fiap.l7.productservice.adapter.dto.ProductDTO;
import com.fiap.l7.productservice.domain.entity.Product;
import com.fiap.l7.productservice.domain.exceptions.NoResultsFoundException;
import com.fiap.l7.productservice.domain.usecase.SaveProductUseCase;
import com.fiap.l7.productservice.domain.usecase.SearchProductsUseCase;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/products")
public class ProductController {

    private final  SaveProductUseCase saveProductUseCase;

    private final SearchProductsUseCase searchProductsUseCase;

    private final JobLauncher jobLauncher;

    private final Job processProductsJob;

    public ProductController(SaveProductUseCase saveProductUseCase, SearchProductsUseCase searchProductsUseCase, JobLauncher jobLauncher, Job processProductsJob) {
        this.saveProductUseCase = saveProductUseCase;
        this.searchProductsUseCase = searchProductsUseCase;
        this.jobLauncher = jobLauncher;
        this.processProductsJob = processProductsJob;
    }

    //endpoint para atualizar apenas a quantidade de estoque de um produto
    @PutMapping("/updateStock/{id}")
    public ResponseEntity<String> updateStock(@PathVariable Long id, @RequestParam Integer quantity) {
        Product product = saveProductUseCase.findById(id).orElseThrow(() -> new NoResultsFoundException("Produto não encontrado"));
        product.setStockQuantity(product.getStockQuantity() + quantity);
        saveProductUseCase.save(product);
        return ResponseEntity.ok().body("Estoque do produto " + product.getName() + " atualizado com sucesso para " + product.getStockQuantity());
    }


    @PostMapping("/saveAll")
    public ResponseEntity<Void> saveProducts(@RequestBody List<ProductDTO> productDTOs) {
        List<Product> products = productDTOs.stream()
                .map(dto -> {
                    Product product = new Product();
                    product.setName(dto.getName());
                    product.setDescription(dto.getDescription());
                    product.setPrice(dto.getPrice());
                    product.setStockQuantity(dto.getStockQuantity());
                    return product;
                })
                .collect(Collectors.toList());
        saveProductUseCase.saveAll(products);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts() {
        List<Product> products = searchProductsUseCase.searchProducts();
        List<ProductDTO> productDTOs = products.stream()
                .map(product -> {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setName(product.getName());
                    productDTO.setDescription(product.getDescription());
                    productDTO.setPrice(product.getPrice());
                    productDTO.setStockQuantity(product.getStockQuantity());
                    return productDTO;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping("/search-one/{id}")
    public int searchOne(@PathVariable Long id) {
        Product product = searchProductsUseCase.searchOne(id);
        ProductDTO productDTO = ProductDTO.convertToDTO(product);
        return productDTO.getStockQuantity();
    }


    @PostMapping("/start-job")
    public ResponseEntity<Void> startJob() {
        try {
            log.info("Iniciando job manualmente");
            jobLauncher.run(processProductsJob, new JobParameters());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

}
