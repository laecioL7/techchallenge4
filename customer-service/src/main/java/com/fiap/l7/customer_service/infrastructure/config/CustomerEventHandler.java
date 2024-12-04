package com.fiap.l7.customer_service.infrastructure.config;


import com.fiap.l7.customer_service.domain.model.Address;
import com.fiap.l7.customer_service.domain.model.Customer;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Customer.class)
public class CustomerEventHandler {

    //para garantir a referencia do endereço com o cliente
    @HandleBeforeCreate
    public void handleCustomerCreate(Customer customer) {
        for (Address address : customer.getAddresses()) {
            address.setCustomer(customer);
        }
    }
}
