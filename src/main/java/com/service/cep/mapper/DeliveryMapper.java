package com.service.cep.mapper;

import com.service.cep.domian.Delivery;
import com.service.cep.dto.delivery.DeliveryCreateDTO;
import com.service.cep.dto.delivery.DeliveryDetailDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {

    DeliveryDetailDTO toDeliveryDetailDTO(Delivery delivery);

    Delivery toDelivery(DeliveryCreateDTO deliveryCreateDTO);
}
