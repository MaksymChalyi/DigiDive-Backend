package com.digidive.digidivebackend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(

        @JsonProperty("email")
        @NotBlank(message = "Email is required")
        String email,

        @JsonProperty("password")
        @NotBlank(message = "Password is required")
        String password,

        @JsonProperty("confirmPassword")
        @NotBlank(message = "Confirm password field is required")
        String confirmPassword
) {
}
