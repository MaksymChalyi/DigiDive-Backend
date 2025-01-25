package com.digidive.digidivebackend.controller.auth;

import com.digidive.digidivebackend.dto.LoginUserDto;
import com.digidive.digidivebackend.dto.RegisterUserDto;
import com.digidive.digidivebackend.dto.response.LoginResponse;
import com.digidive.digidivebackend.entity.User;
import com.digidive.digidivebackend.security.jwt.JwtService;
import com.digidive.digidivebackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final UserDetailsService userDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        String jwtToken = jwtService.generateToken(userDetailsService.loadUserByUsername(loginUserDto.email()));

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getJwtLifetime().toMillis());
        return ResponseEntity.ok(loginResponse);
    }


}
