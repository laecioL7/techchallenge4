package com.fiap.l7.order_service.infraestructure.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.l7.order_service.application.usecase.RequestDeliveryUseCase;
import com.fiap.l7.order_service.domain.model.Order;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Log4j2
@Configuration
public class PaymentCallbackListener {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RequestDeliveryUseCase requestDeliveryUseCase;

    public PaymentCallbackListener(RequestDeliveryUseCase requestDeliveryUseCase) {
        this.requestDeliveryUseCase = requestDeliveryUseCase;
    }

    @Bean
    public Consumer<String> paymentCallbackConsumer() {
        return order -> {
            log.info("Recebendo mensagem: " + order);
            try {
                Order orderModel = objectMapper.readValue(order, Order.class);
                log.info("Mensagem lida, seguindo processo...");

                /*TODO: aqui teria recebido um callback via webhook e o webhook postaria nessa fila
                *  */

                requestDeliveryUseCase.execute(orderModel);

            } catch (JsonProcessingException e) {
                log.error("Erro ao desserializar a mensagem", e);
            } catch (Exception e) {
                log.error("Erro ao processar", e);
            }
            //TODO: por exemplo, registrar a entrega e simular a chamada para o serviço de entrega externo.
        };
    }
}
