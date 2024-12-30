package com.service.cep.dto.delivery;

import com.service.cep.domian.Delivery;
import com.service.cep.domian.enums.DeliveryStatus;

public record DeliveryDetailDTO(
        Long id,
        String origin,
        String destination,
        String trackingCode,
        DeliveryStatus status
) {
    public DeliveryDetailDTO(Delivery delivery){
        this(
                delivery.getId(),
                delivery.getOrigin(),
                delivery.getDestination(),
                delivery.getTrackingCode(),
                DeliveryStatus.valueOf(delivery.getTrackingCode())
        );
    }
}
