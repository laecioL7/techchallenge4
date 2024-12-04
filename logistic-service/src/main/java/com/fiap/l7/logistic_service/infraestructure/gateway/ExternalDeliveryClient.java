package com.fiap.l7.logistic_service.infraestructure.gateway;

import com.fiap.l7.logistic_service.domain.model.Order;
import org.springframework.stereotype.Component;

@Component
public class ExternalDeliveryClient {

    //TODO: add endereço
    public boolean requestDelivery(Order order) {
        // Simulação de chamada a um serviço externo, correios, braspress, loggi etc
        System.out.println("Simulando chamada ao serviço de entrega para o pedido: " + order.getId());

        // Simulando sucesso na integração
        return true;
    }
}
