package com.service.cep.service;

import java.util.Map;

public interface HttpClientService {
    String sendGetRequest(String url, Map<String, String> queryParams);
}
