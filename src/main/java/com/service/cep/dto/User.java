package com.service.cep.dto;

public record User(
        String password,
        String clientId,
        String grantType,
        String username
) {
}
