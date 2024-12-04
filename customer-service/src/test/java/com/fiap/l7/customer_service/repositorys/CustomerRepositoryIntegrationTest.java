package com.fiap.l7.customer_service.repositorys;

import com.fiap.l7.customer_service.domain.model.Customer;
import com.fiap.l7.customer_service.domain.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CustomerRepositoryIntegrationTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testSaveCustomer() {
        Customer customer = new Customer();
        customer.setName("Eren Yager");
        customer.setEmail("eren@teste.com");
        customer.setPhone("123456789");

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

        customerRepository.save(customer1);
        customerRepository.save(customer2);

        List<Customer> customers = customerRepository.findAll();
        assertEquals(2, customers.size());
    }
}