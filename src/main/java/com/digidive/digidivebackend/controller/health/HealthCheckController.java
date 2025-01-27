package com.digidive.digidivebackend.controller.health;

import com.digidive.digidivebackend.dto.response.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/unsecured")
    public String unsecuredData() {
        return "Unsecured data";
    }

    @GetMapping("/secured")
    public String securedData() {
        return "Secured data";
    }

    @GetMapping("/info")
    public String userData(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponseDto<?>> test() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseDto.builder()
                        .isSuccess(true)
                        .message("Let's learn Spring Security with JWT!")
                        .response(null)
                        .build());
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponseDto<?>> userDashboard() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseDto.builder()
                        .isSuccess(true)
                        .message("User dashboard!")
                        .response(null)
                        .build());
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponseDto<?>> AdminDashboard() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseDto.builder()
                        .isSuccess(true)
                        .message("Admin dashboard!")
                        .response(null)
                        .build());
    }

    @GetMapping("/adminOrUser")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponseDto<?>> AdminOrUserContent() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseDto.builder()
                        .isSuccess(true)
                        .message("Admin or User Content!")
                        .response(null)
                        .build());
    }

}
