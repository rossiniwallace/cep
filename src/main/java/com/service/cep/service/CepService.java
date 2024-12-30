package com.service.cep.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.cep.domian.ExternalQueryLog;
import com.service.cep.domian.enums.HttpMethod;
import com.service.cep.dto.webservice.CepResponseDTO;
import com.service.cep.utils.DummyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CepService {

    @Autowired
    private HttpClientService httpClientService;

    @Autowired
    private ExternalQueryLogService externalQueryLogService;

    @Value("${wiremock.api.url}")
    private String url;

    public CepResponseDTO getCepInfo(String cep){
        try {
            Map<String, String> queryParams = new HashMap<>();
            queryParams.put("cep", cep);

            String response = httpClientService.sendGetRequest(url, queryParams);

            ExternalQueryLog log = new ExternalQueryLog();
            log.setUrl(url);
            log.setHttpMethod(HttpMethod.GET);
            log.setRequestParams(queryParams.toString());
            log.setTimestamp(new Date());
            log.setResponse(response);

            externalQueryLogService.createLog(log);
            CepResponseDTO responseDTO = DummyUtils.converterParaDTO(response);

            return responseDTO;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse CEP response", e);
        }
    }
}
