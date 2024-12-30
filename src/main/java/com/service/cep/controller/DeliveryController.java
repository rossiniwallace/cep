package com.service.cep.controller;

import com.service.cep.domian.enums.DeliveryStatus;
import com.service.cep.dto.delivery.DeliveryCreateDTO;
import com.service.cep.dto.delivery.DeliveryDetailDTO;
import com.service.cep.dto.delivery.DeliveryFilter;
import com.service.cep.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping
    public ResponseEntity<Page<DeliveryDetailDTO>> findPageable(
            DeliveryFilter filter,
            @PageableDefault(page = 0, size = 10) Pageable pageable){
        return deliveryService.findPageable(pageable, filter);
    }

    @PostMapping
    public ResponseEntity<DeliveryDetailDTO> create(@RequestBody DeliveryCreateDTO dto) {
        return deliveryService.createDelivery(dto);
    }

    @PutMapping("/{trackingCode}")
    public ResponseEntity<DeliveryDetailDTO> updateDeliveryStatus(
            @PathVariable String trackingCode,
            @RequestParam DeliveryStatus status
    ){
        return deliveryService.updateStatus(trackingCode, status);
    }
}
