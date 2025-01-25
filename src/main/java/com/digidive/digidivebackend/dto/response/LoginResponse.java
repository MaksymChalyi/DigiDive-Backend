package com.digidive.digidivebackend.dto.response;

public record LoginResponse(
        String token,
        long expiresIn
) {
}
