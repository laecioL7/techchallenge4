package com.fiap.l7.logistic_service.application.usecase;

import com.fiap.l7.logistic_service.application.usecase.dto.CustomerDto;
import com.fiap.l7.logistic_service.domain.model.DeliveryRecord;
import com.fiap.l7.logistic_service.domain.model.DeliveryStatus;
import com.fiap.l7.logistic_service.infraestructure.gateway.ConsultDeliveryResponse;
import com.fiap.l7.logistic_service.infraestructure.repository.DeliveryRecordRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Log4j2
public class ConsultDeliveryStatusUseCase {

    private final WebClient.Builder defaultWebClientBuilder;
    private final DeliveryRecordRepository deliveryRecordRepository;

    @Value("${delivery.api.url}")
    private String externalApiUrl;

    @Value("${delivery.api.key}")
    private String appKey;

    public ConsultDeliveryStatusUseCase(WebClient.Builder defaultWebClientBuilder, DeliveryRecordRepository deliveryRecordRepository) {
        this.defaultWebClientBuilder = defaultWebClientBuilder;
        this.deliveryRecordRepository = deliveryRecordRepository;
    }

    public Mono<ConsultDeliveryResponse> execute(String trackingCode) {
        log.info("Buscando informações do pedido");
        log.info("Executando chamada para a api de entrega externa...");
        // Asynchronous call to external API to request delivery
        return defaultWebClientBuilder.build()
                .get()
                .uri(externalApiUrl + "/{trackingCode}", trackingCode)
                .header("appKey", appKey)
                .retrieve()
                .bodyToMono(ConsultDeliveryResponse.class);
    }
}
