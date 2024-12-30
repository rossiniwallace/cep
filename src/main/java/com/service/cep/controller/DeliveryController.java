package com.service.cep.controller;

import com.service.cep.dto.delivery.DeliveryCreateDTO;
import com.service.cep.dto.delivery.DeliveryDetailDTO;
import com.service.cep.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping
    public DeliveryDetailDTO findByTrackingCode(@RequestParam String trackingCode) {
        return deliveryService.findByTrackingCode(trackingCode);
    }

    @PostMapping
    public DeliveryDetailDTO create(@RequestBody DeliveryCreateDTO dto) {
        return deliveryService.createDelivery(dto);
    }

    //TODO endpoint -> buscar por trackingCode
    //TODO endpoint -> listar entregas
    //TODO endpoint -> atualizar entrega por trackingCode
}
