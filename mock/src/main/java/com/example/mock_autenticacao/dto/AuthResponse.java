package com.example.mock_autenticacao.dto;

public class AuthResponse {
    private String authenticated;

    public AuthResponse(String authenticated) {
        this.authenticated = authenticated;
    }

    public String getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(String authenticated) {
        this.authenticated = authenticated;
    }
}
