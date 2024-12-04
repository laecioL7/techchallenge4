package com.fiap.l7.order_service.infraestructure.adapters.controller;

import com.fiap.l7.order_service.application.usecase.CreateOrderUseCase;
import com.fiap.l7.order_service.application.usecase.RequestPaymentUseCase;
import com.fiap.l7.order_service.domain.model.Order;
import com.fiap.l7.order_service.domain.model.Payment;
import com.fiap.l7.order_service.infraestructure.adapters.dto.OrderDto;
import com.fiap.l7.order_service.infraestructure.adapters.dto.PaymentDto;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final CreateOrderUseCase createOrderUseCase;
    private final RequestPaymentUseCase requestPaymentUseCase;

    @Inject
    public PaymentController(CreateOrderUseCase createOrderUseCase, RequestPaymentUseCase requestPaymentUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.requestPaymentUseCase = requestPaymentUseCase;
    }

    @PostMapping("/payment")
    public Payment createOrder(@RequestBody PaymentDto paymentDto) {
        OrderDto orderDto = paymentDto.getOrderDto();
        Order order = new Order();
        order.setCustomerId(orderDto.getCustomerId());
        order.setItems(orderDto.getItems());
        order = createOrderUseCase.execute(order);
        return requestPaymentUseCase.execute(order, paymentDto);
    }

}
