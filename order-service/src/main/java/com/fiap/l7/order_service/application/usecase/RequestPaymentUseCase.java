package com.fiap.l7.order_service.application.usecase;

import com.fiap.l7.order_service.domain.model.Order;
import com.fiap.l7.order_service.domain.model.Payment;
import com.fiap.l7.order_service.domain.model.PaymentStatus;
import com.fiap.l7.order_service.infraestructure.adapters.dto.PaymentDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;

@Log4j2
public class RequestPaymentUseCase {

    @Value("message.payment.queue-name")
    private String queueName;

    public Payment execute(Order order, PaymentDto paymentDto) {
        log.info("publicando pedido");
        //TODO:aqui faria a integração com o gateway de pagamento recebendo um protocolo para processar o callback
        //gateway call webclient
        String prococol = "12312-123123-132123-123123";

        Payment payment = new Payment();
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        payment.setOrderId(order.getId());
        payment.setProtocol(prococol);
        return payment;
    }

}
