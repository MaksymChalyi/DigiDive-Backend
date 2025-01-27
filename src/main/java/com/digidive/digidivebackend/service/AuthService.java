package com.digidive.digidivebackend.service;

import com.digidive.digidivebackend.dto.request.SignInRequestDto;
import com.digidive.digidivebackend.dto.request.SignUpRequestDto;
import com.digidive.digidivebackend.dto.response.ApiResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<ApiResponseDto<?>> signUpUser(SignUpRequestDto input);

    ResponseEntity<ApiResponseDto<?>> signInUser(SignInRequestDto signInRequestDto);
}
