package com.example.mock_autenticacao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mock_autenticacao.dto.AuthResponse;

@RestController
@RequestMapping("/teste")
public class SimpleController {

    @PostMapping("/{dash}")
    public ResponseEntity<AuthResponse> getAuthStatus(@PathVariable String dash) {
        AuthResponse response = new AuthResponse("nao-autenticado");
        if("dashboards:130321860990926919292642443324377346416".equals(dash)){
            response = new AuthResponse("dashboards");
            return ResponseEntity.ok(response);
        }
        if("data_and_schedule:43829315022000650150631673672638531889".equals(dash)){
            response = new AuthResponse("data_and_schedule");
        }     
        return ResponseEntity.ok(response);   
    }
}
    