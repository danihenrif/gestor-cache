package br.ufrn.imd.distribuida.cache_manager.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenValidationService {
    private final RestTemplate restTemplate;

    public TokenValidationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String validateToken(String token) {
        String url = "http://10.7.40.177:8000/token/" + token;

        // Configurar o cabeçalho da requisição
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                String responseBody = response.getBody();
                if (responseBody != null) {
                    if (responseBody.contains("\"authenticated\": \"dashboards\"")) {
                        return "dashboards";
                    } else if (responseBody.contains("\"authenticated\": \"data_and_schedule\"")) {
                        return "data_and_schedule";
                    }
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
