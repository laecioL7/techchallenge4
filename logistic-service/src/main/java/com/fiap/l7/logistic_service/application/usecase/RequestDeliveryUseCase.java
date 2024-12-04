package com.fiap.l7.logistic_service.application.usecase;

import com.fiap.l7.logistic_service.application.usecase.dto.AddressDto;
import com.fiap.l7.logistic_service.application.usecase.dto.CustomerDto;
import com.fiap.l7.logistic_service.domain.model.DeliveryRecord;
import com.fiap.l7.logistic_service.domain.model.DeliveryStatus;
import com.fiap.l7.logistic_service.domain.model.Order;
import com.fiap.l7.logistic_service.infraestructure.gateway.DeliveryRequestMapper;
import com.fiap.l7.logistic_service.infraestructure.gateway.DeliveryResponse;
import com.fiap.l7.logistic_service.infraestructure.repository.DeliveryRecordRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Comparator;

@Log4j2
public class RequestDeliveryUseCase {

    private final WebClient.Builder defaultWebClientBuilder;
    private final DeliveryRecordRepository deliveryRecordRepository;

    @Value("${delivery.api.url}")
    private String externalApiUrl;

    @Value("${delivery.api.key}")
    private String appKey;

    public RequestDeliveryUseCase(WebClient.Builder defaultWebClientBuilder, DeliveryRecordRepository deliveryRecordRepository) {
        this.defaultWebClientBuilder = defaultWebClientBuilder;
        this.deliveryRecordRepository = deliveryRecordRepository;
    }

    public void execute(Order order, CustomerDto customerDto) {
        log.info("Selecionando endereço principal do cliente");
        AddressDto selectAddress = customerDto.getAddresses().stream()
                .min(Comparator.comparingInt(AddressDto::getPriority)).orElseThrow(EntityNotFoundException::new);

        log.info("Endereço de entrega: {} , {}", selectAddress.getStreet(), selectAddress.getCity());

        log.info("Executando chamada para a api de entrega externa");
        // Asynchronous call to external API to request delivery
        defaultWebClientBuilder.build()
                .post()
                .uri(externalApiUrl)
                .header("appKey", appKey)
                .bodyValue(DeliveryRequestMapper.createDeliveryRequest(order,customerDto, selectAddress))
                .retrieve()
                .bodyToMono(DeliveryResponse.class)
                .subscribe(response -> {
                    log.info("Salvando o registro da entrega do cliente");
                    DeliveryRecord deliveryRecord = new DeliveryRecord();
                    deliveryRecord.setOrderId(order.getId());
                    deliveryRecord.setDeliveryStatus(DeliveryStatus.PENDING);
                    deliveryRecord.setTrackingCode(response.getTrackingCode());
                    deliveryRecordRepository.save(deliveryRecord);
                });
    }
}
