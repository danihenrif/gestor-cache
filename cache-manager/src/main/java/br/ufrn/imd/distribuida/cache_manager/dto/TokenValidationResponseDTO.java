package br.ufrn.imd.distribuida.cache_manager.dto;

public class TokenValidationResponseDTO {
    private String authenticated;

    public String getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(String authenticated) {
        this.authenticated = authenticated;
    }
}