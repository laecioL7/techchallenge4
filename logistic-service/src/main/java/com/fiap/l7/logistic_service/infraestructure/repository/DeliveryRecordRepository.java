package com.fiap.l7.logistic_service.infraestructure.repository;

import com.fiap.l7.logistic_service.domain.model.DeliveryRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRecordRepository extends JpaRepository<DeliveryRecord, Long> {

    DeliveryRecord findByTrackingCode(String trackingCode);

}