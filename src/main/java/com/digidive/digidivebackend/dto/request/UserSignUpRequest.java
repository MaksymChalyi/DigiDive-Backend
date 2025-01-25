package com.digidive.digidivebackend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserSignUpRequest(

        @JsonProperty("email")
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email is required")
        String email,

        @JsonProperty("password")
        @NotBlank(message = "Password is required")
        String password,

        @JsonProperty("confirmPassword")
        @NotBlank(message = "Confirm password field is required")
        @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
        String confirmPassword
) {
}
