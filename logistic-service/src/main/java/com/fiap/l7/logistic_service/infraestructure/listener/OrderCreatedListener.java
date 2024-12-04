package com.fiap.l7.logistic_service.infraestructure.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.l7.logistic_service.application.usecase.GetUserAddressUseCase;
import com.fiap.l7.logistic_service.application.usecase.RequestDeliveryUseCase;
import com.fiap.l7.logistic_service.application.usecase.dto.CustomerDto;
import com.fiap.l7.logistic_service.domain.model.Order;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;
import java.util.function.Consumer;

@Log4j2
@Configuration
public class OrderCreatedListener {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RequestDeliveryUseCase requestDeliveryUseCase;
    private final GetUserAddressUseCase getUserAddressUseCase;

    public OrderCreatedListener(RequestDeliveryUseCase requestDeliveryUseCase, GetUserAddressUseCase getUserAddressUseCase) {
        this.requestDeliveryUseCase = requestDeliveryUseCase;
        this.getUserAddressUseCase = getUserAddressUseCase;
    }

    @Bean
    public Consumer<String> orderConsumer() {
        return order -> {
            log.info("Recebendo mensagem: " + order);
            try {
                Order orderModel = objectMapper.readValue(order, Order.class);
                log.info("Mensagem lida, seguindo processo...");

                CustomerDto customer = getUserAddressUseCase.execute(orderModel);

                requestDeliveryUseCase.execute(orderModel, customer);

            } catch (JsonProcessingException e) {
                log.error("Erro ao desserializar a mensagem", e);
            } catch (Exception e) {
                log.error("Erro ao processar", e);
            }
            //TODO: por exemplo, registrar a entrega e simular a chamada para o serviço de entrega externo.
        };
    }
}
