package com.fiap.l7.customer_service.repositorys;

import com.fiap.l7.customer_service.domain.model.Customer;
import com.fiap.l7.customer_service.domain.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CustomerRepositoryUnitTest {

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCustomer() {
        Customer customer = new Customer();
        customer.setName("Eren Yager");
        customer.setEmail("eren@teste.com");
        customer.setPhone("123456789");

        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        customerRepository.save(customer);
        assertTrue(customerRepository.findById(customer.getId()).isPresent());
    }

    @Test
    void testListCustomers() {
        Customer customer1 = new Customer();
        customer1.setName("Mikasa Ackerman");
        customer1.setEmail("mikasa@teste.com");
        customer1.setPhone("987654321");

        Customer customer2 = new Customer();
        customer2.setName("Armin Arlert");
        customer2.setEmail("armin@teste.com");
        customer2.setPhone("123123123");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        List<Customer> customers = customerRepository.findAll();
        assertEquals(2, customers.size());
    }
}
