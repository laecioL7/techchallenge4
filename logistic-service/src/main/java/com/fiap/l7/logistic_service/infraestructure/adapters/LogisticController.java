package com.fiap.l7.logistic_service.infraestructure.adapters;

import com.fiap.l7.logistic_service.application.usecase.ConsultDeliveryStatusUseCase;
import com.fiap.l7.logistic_service.infraestructure.gateway.ConsultDeliveryResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@RequestMapping("/logistic")
public class LogisticController {

    private final ConsultDeliveryStatusUseCase consultDeliveryStatusUseCase;

    public LogisticController(ConsultDeliveryStatusUseCase consultDeliveryStatusUseCase) {
        this.consultDeliveryStatusUseCase = consultDeliveryStatusUseCase;
    }

    @GetMapping("/status/{trackingNumber}")
    public Mono<ConsultDeliveryResponse> createOrder(@PathVariable String trackingNumber) {
        return consultDeliveryStatusUseCase.execute(trackingNumber);
    }

}
