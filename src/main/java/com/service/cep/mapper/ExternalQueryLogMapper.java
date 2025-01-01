package com.service.cep.mapper;


import com.service.cep.domian.ExternalQueryLog;
import com.service.cep.dto.logs.ExternalQueryLogDetailDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExternalQueryLogMapper {

    ExternalQueryLogDetailDTO toExternalQueryLogDTO(ExternalQueryLog log);
    ExternalQueryLog toExternalQueryLog(ExternalQueryLog log);
}
