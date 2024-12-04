package com.fiap.l7.logistic_service.infraestructure.gateway;

import com.fiap.l7.logistic_service.application.usecase.dto.AddressDto;
import com.fiap.l7.logistic_service.application.usecase.dto.CustomerDto;
import com.fiap.l7.logistic_service.domain.model.Order;

public class DeliveryRequestMapper {

    public static DeliveryRequest createDeliveryRequest(Order order, CustomerDto customer, AddressDto address) {
        DeliveryRequest deliveryRequest = new DeliveryRequest();
        deliveryRequest.setName(customer.getName());
        deliveryRequest.setEmail(customer.getEmail());
        deliveryRequest.setPhone(customer.getPhone());
        deliveryRequest.setStreet(address.getStreet());
        deliveryRequest.setNumber(address.getNumber());
        deliveryRequest.setComplement(address.getComplement());
        deliveryRequest.setCity(address.getCity());
        deliveryRequest.setState(address.getState());
        deliveryRequest.setCountry(address.getCountry());
        deliveryRequest.setZipCode(address.getZipCode());
        return deliveryRequest;
    }
}
