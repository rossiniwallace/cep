package com.service.cep.dto.delivery;

public record DeliveryCreateDTO(
        String origin,
        String destination,
        String trackingCode
) {
}
