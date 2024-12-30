package com.service.cep.dto.delivery;

import org.springframework.web.bind.annotation.RequestParam;

public record DeliveryFilter(
        @RequestParam(required = false)
        String trackingCode,
        @RequestParam(required = false)
        String status
) {

}
