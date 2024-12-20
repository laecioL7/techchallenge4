package com.fiap.l7.customer_service.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.l7.customer_service.domain.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerRestRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateCustomer() throws Exception {
        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"John Yager\", \"email\": \"john.Yager@example.com\", \"phone\": \"123456789\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetCustomer() throws Exception {
        //assert
        //save a customer
        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getCustomerAsJson())).
                andExpect(status().isCreated());

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk());
    }

    String getCustomerAsJson() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Eren Yager");
        customer.setEmail("eren@teste.com");
        customer.setPhone("123456789");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(customer);
            System.out.println(jsonString);
            return jsonString;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }

}