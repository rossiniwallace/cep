package com.service.cep.service;

import com.service.cep.domian.Delivery;
import com.service.cep.domian.enums.DeliveryStatus;
import com.service.cep.dto.delivery.DeliveryCreateDTO;
import com.service.cep.dto.delivery.DeliveryDetailDTO;
import com.service.cep.dto.webservice.CepResponseDTO;
import com.service.cep.mapper.DeliveryMapper;
import com.service.cep.repository.DeliveryRepository;
import com.service.cep.utils.DummyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private CepService cepService;

    @Autowired
    private DeliveryMapper deliveryMapper;

    public DeliveryDetailDTO createDelivery(DeliveryCreateDTO dto) {

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

        return deliveryMapper.toDeliveryDetailDTO(delivery);
    }

}
