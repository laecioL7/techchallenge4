package com.fiap.l7.customer_service.domain.repository;

import com.fiap.l7.customer_service.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}