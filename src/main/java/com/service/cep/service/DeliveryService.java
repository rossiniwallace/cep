package com.service.cep.service;

import com.service.cep.domian.Delivery;
import com.service.cep.domian.enums.DeliveryStatus;
import com.service.cep.dto.delivery.DeliveryCreateDTO;
import com.service.cep.dto.delivery.DeliveryDetailDTO;
import com.service.cep.dto.delivery.DeliveryFilter;
import com.service.cep.dto.webservice.CepResponseDTO;
import com.service.cep.mapper.DeliveryMapper;
import com.service.cep.repository.DeliveryRepository;
import com.service.cep.repository.DeliverySpecification;
import com.service.cep.utils.DummyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Date;
import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private CepService cepService;

    @Autowired
    private DeliveryMapper deliveryMapper;

    public ResponseEntity<DeliveryDetailDTO> createDelivery(DeliveryCreateDTO dto) {

      //TODO realizar tratativa de erros

        Date now  = new Date();

        CepResponseDTO originDTO = cepService.getCepInfo(dto.origin());
        CepResponseDTO destinationDTO = cepService.getCepInfo(dto.destination());

        String formattedOrigin = DummyUtils.formatAddress(originDTO);
        String formattedDestination = DummyUtils.formatAddress(destinationDTO);

        String trackingCode = DummyUtils.generateTrackingCode(destinationDTO.getUf());

        Delivery delivery = new Delivery();
        delivery.setOrigin(formattedOrigin);
        delivery.setDestination(formattedDestination);
        delivery.setStatus(DeliveryStatus.PENDING);
        delivery.setTrackingCode(trackingCode);
        delivery.setOrderDate(now);

        deliveryRepository.saveAndFlush(delivery);
        DeliveryDetailDTO deliveryDetailDTO = deliveryMapper.toDeliveryDetailDTO(delivery);
        URI uri = URI.create("/deliveries/" + delivery.getTrackingCode());

        return ResponseEntity.created(uri).body(deliveryDetailDTO);
    }

    public ResponseEntity<DeliveryDetailDTO> findByTrackingCode(String trackingCode) {

        Optional<Delivery> optionalDelivery = deliveryRepository.findByTrackingCode(trackingCode);

        if(optionalDelivery.isEmpty()){
            throw new RuntimeException("Delivery not found for tracking code: " + trackingCode);
        };

        DeliveryDetailDTO deliveryDetailDTO = deliveryMapper.toDeliveryDetailDTO(optionalDelivery.get());

        return ResponseEntity.ok(deliveryDetailDTO);
    }

    public ResponseEntity<Page<DeliveryDetailDTO>> findPageable(Pageable pageable, DeliveryFilter filter) {

        var specificatin = DeliverySpecification.filterBy(filter);

        Page<Delivery> deliveries = deliveryRepository.findAll(specificatin, pageable);

        Page<DeliveryDetailDTO> map = deliveries.map(deliveryMapper::toDeliveryDetailDTO);

        return ResponseEntity.ok(map);
    }

    public ResponseEntity<DeliveryDetailDTO> updateStatus(String trackingCode, DeliveryStatus status) {

        Delivery delivery = deliveryRepository.findByTrackingCode(trackingCode)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        delivery.setStatus(status);

        deliveryRepository.save(delivery);
        DeliveryDetailDTO deliveryDetailDTO = deliveryMapper.toDeliveryDetailDTO(delivery);

        return ResponseEntity.ok(deliveryDetailDTO);
    }
}
