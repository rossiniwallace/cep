package com.service.cep.dto.delivery;

import com.service.cep.domian.enums.DeliveryStatus;

public record DeliveryDetailDTO(
        Long id,
        String origin,
        String destination,
        String trackingCode,
        DeliveryStatus status
) {

}
