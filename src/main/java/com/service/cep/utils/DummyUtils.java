package com.service.cep.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.cep.dto.webservice.CepResponseDTO;

import java.text.MessageFormat;
import java.util.Random;

public class DummyUtils {

    public static String generateTrackingCode(String uf){

        if(uf.isBlank()){
            throw new IllegalArgumentException("UF cannot be null or empty");
        }

        Random random = new Random();

        int randomNumber = 10000000 + random.nextInt(90000000);

        return uf.toUpperCase() + randomNumber;
    }

    public static String formatAddress(CepResponseDTO dto) {
        return MessageFormat.format("{0} - {1}, {2} - {3}, {4}",
                dto.getLogradouro(),
                dto.getBairro(),
                dto.getLocalidade(),
                dto.getUf(),
                dto.getCep()
        );
    }

    public static CepResponseDTO converterParaDTO(String responseJson) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseJson, CepResponseDTO.class);
    }
}
