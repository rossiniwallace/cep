package com.service.cep.dto.logs;

import com.service.cep.domian.ExternalQueryLog;
import com.service.cep.domian.enums.HttpMethod;

import java.util.Date;

public record ExternalQueryLogDetailDTO(
        Long id,
        String url,
        HttpMethod httpMethod,
        String requestParams,
        String response,
        Date timestamp
) {
    public ExternalQueryLogDetailDTO(ExternalQueryLog log) {
        this(
                log.getId(),
                log.getUrl(),
                log.getHttpMethod(),
                log.getRequestParams(),
                log.getResponse(),
                log.getTimestamp()
        );
    }
}
