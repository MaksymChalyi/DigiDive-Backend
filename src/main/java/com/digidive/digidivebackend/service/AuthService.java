package com.digidive.digidivebackend.service;

import com.digidive.digidivebackend.dto.LoginUserDto;
import com.digidive.digidivebackend.dto.request.SignUpRequestDto;
import com.digidive.digidivebackend.dto.response.ApiResponseDto;
import com.digidive.digidivebackend.entity.User;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<ApiResponseDto<?>> signUpUser(SignUpRequestDto input);

    User authenticate(LoginUserDto input);
}
