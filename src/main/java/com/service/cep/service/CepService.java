package com.service.cep.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.cep.domian.ExternalQueryLog;
import com.service.cep.domian.enums.HttpMethod;
import com.service.cep.dto.webservice.CepResponseDTO;
import com.service.cep.exception.BadRequestException;
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

            if(cep == null || cep.isBlank()) {
                throw new BadRequestException("CEP não pode estar vazio ou nulo");
            }

            Map<String, String> queryParams = new HashMap<>();
            queryParams.put("cep", cep);

            String response = httpClientService.sendGetRequest(url, queryParams);

            if(response == null) {
                throw new BadRequestException("CEP não encontrado:" + cep);
            }

            ExternalQueryLog log = new ExternalQueryLog();
            log.setUrl(url);
            log.setHttpMethod(HttpMethod.GET);
            log.setRequestParams(queryParams.toString());
            log.setTimestamp(new Date());
            log.setResponse(response);

            externalQueryLogService.createLog(log);
            CepResponseDTO responseDTO = DummyUtils.converterParaDTO(response);

            return responseDTO;

        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e){
            throw new BadRequestException("CEP não encontrado: " + cep);
        }
    }
}
