package com.example.EcommUser.Dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String token;
    private String tokenType = "Bearer ";
    private Boolean success;

    public AuthResponseDto(String token, Boolean success) {
        this.token = token;
        this.success = success;
    }
}
