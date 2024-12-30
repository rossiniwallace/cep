package com.service.cep.service;

import com.service.cep.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

@Service
public class HttpClientImpl implements HttpClientService {

    public String sendGetRequest(String url, Map<String, String> queryParams){
        try {
            String finalUrl = buildUrlWithParams(url, queryParams);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(finalUrl))
                    .header("Content-Type", "application/json")
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() == 404){
                throw new BadRequestException("CEP não encontrado: " + queryParams);
            }

            return response.body();
        } catch (Exception e) {
            throw new RuntimeException("Error while sending GET request", e);
        }
    }

    private String buildUrlWithParams(String url, Map<String, String> queryParams) {
        if (queryParams != null && !queryParams.isEmpty()) {
            StringJoiner sj = new StringJoiner("&", url + "?", "");
            Set<Map.Entry<String, String>> entrySet = queryParams.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                sj.add(entry.getKey() + "=" + entry.getValue());
            }
            return sj.toString();
        }
        return url;
    }
}
