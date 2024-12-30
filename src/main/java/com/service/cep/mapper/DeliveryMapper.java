package com.service.cep.mapper;

import com.service.cep.domian.Delivery;
import com.service.cep.dto.delivery.DeliveryCreateDTO;
import com.service.cep.dto.delivery.DeliveryDetailDTO;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {

    DeliveryDetailDTO toDeliveryDetailDTO(Delivery delivery);

    DeliveryDetailDTO toDeliveryDetailDTO(Optional<Delivery> delivery);

    Delivery toDelivery(DeliveryCreateDTO deliveryCreateDTO);
}
