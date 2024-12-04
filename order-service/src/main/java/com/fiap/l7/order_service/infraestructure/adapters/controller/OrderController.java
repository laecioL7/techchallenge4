package com.fiap.l7.order_service.infraestructure.adapters.controller;

import com.fiap.l7.order_service.infraestructure.adapters.dto.OrderDto;
import com.fiap.l7.order_service.application.usecase.CreateOrderUseCase;
import com.fiap.l7.order_service.domain.model.Order;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/order")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    @Inject
    public OrderController(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping("/create-order")
    public Order createOrder(@RequestBody OrderDto orderDto) {
        Order order = new Order();
        order.setCustomerId(orderDto.getCustomerId());
        order.setItems(orderDto.getItems());
        return createOrderUseCase.execute(order);
    }

}
