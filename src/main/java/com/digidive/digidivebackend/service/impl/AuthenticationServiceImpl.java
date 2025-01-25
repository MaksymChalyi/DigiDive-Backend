package com.digidive.digidivebackend.service.impl;

import com.digidive.digidivebackend.dto.LoginUserDto;
import com.digidive.digidivebackend.dto.RegisterUserDto;
import com.digidive.digidivebackend.entity.User;
import com.digidive.digidivebackend.repository.UserRepository;
import com.digidive.digidivebackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public User signup(RegisterUserDto input) {
        User user = User.builder()
                .email(input.email())
                .password(passwordEncoder.encode(input.password()))
                .build();
        return userRepository.save(user);
    }

    @Override
    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.email(),
                        input.password()
                )
        );
        return userRepository.findByEmail(input.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
