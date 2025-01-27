package com.digidive.digidivebackend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignInRequestDto(

        @NotBlank(message = "Email is required!")
        @Email
        String email,

        @NotBlank(message = "Password is required!")
        String password) {
}
