package com.fiap.l7.productservice.controller;

import com.fiap.l7.productservice.domain.usecase.SaveProductUseCase;
import com.fiap.l7.productservice.adapter.controller.ProductController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {
    private MockMvc mockMvc;

    @Mock
    private SaveProductUseCase saveProductUseCase;

    @InjectMocks
    private ProductController productController;

    @Test
    void testGetAllProducts() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        mockMvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
