package br.ufrn.imd.distribuida.cache_manager.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.ufrn.imd.distribuida.cache_manager.dto.TokenValidationResponseDTO;

@Service
public class TokenValidationService {
    private final RestTemplate restTemplate;

    public TokenValidationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String validateToken(String token) {
        String url = "http://mock:8080/teste/" + token;


        // Configurar o cabeçalho da requisição
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<TokenValidationResponseDTO> response = restTemplate.exchange(url, HttpMethod.POST, entity, TokenValidationResponseDTO.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                TokenValidationResponseDTO responseBody = response.getBody();
                if (responseBody != null) {
                    String authenticated = responseBody.getAuthenticated();
                    if ("dashboards".equals(authenticated)) {
                        return "dashboards";
                    } else if ("data_and_schedule".equals(authenticated)) {
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