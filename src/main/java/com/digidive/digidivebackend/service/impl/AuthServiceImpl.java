package com.digidive.digidivebackend.service.impl;

import com.digidive.digidivebackend.security.jwt.JwtUtils;
import com.digidive.digidivebackend.dto.request.SignInRequestDto;
import com.digidive.digidivebackend.dto.request.SignUpRequestDto;
import com.digidive.digidivebackend.dto.response.ApiResponseDto;
import com.digidive.digidivebackend.entity.Role;
import com.digidive.digidivebackend.entity.RoleFactory;
import com.digidive.digidivebackend.entity.User;
import com.digidive.digidivebackend.exceptions.PasswordMismatchException;
import com.digidive.digidivebackend.exceptions.RoleNotFoundException;
import com.digidive.digidivebackend.exceptions.UserAlreadyExistsException;
import com.digidive.digidivebackend.repository.UserRepository;
import com.digidive.digidivebackend.service.AuthService;
import com.digidive.digidivebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleFactory roleFactory;
    private final JwtUtils jwtUtils;

    @Override
    public ResponseEntity<ApiResponseDto<?>> signUpUser(SignUpRequestDto signUpRequestDto) throws UserAlreadyExistsException, RoleNotFoundException {
        if (userService.existByEmail(signUpRequestDto.email())) {
            throw new UserAlreadyExistsException("Registration Failed: Provided email already exists. Try sign in or provide another email.");
        }
        if (!signUpRequestDto.password().equals(signUpRequestDto.confirmPassword())) {
            throw new PasswordMismatchException("Password and Confirm Password do not match!");
        }

        User user = createUser(signUpRequestDto);
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseDto.builder()
                        .isSuccess(true)
                        .message("User account has been successfully created!")
                        .build()
        );
    }

    private User createUser(SignUpRequestDto signUpRequestDto) throws RoleNotFoundException {
        return User.builder()
                .email(signUpRequestDto.email())
                .password(passwordEncoder.encode(signUpRequestDto.password()))
                .roles(determineRoles(signUpRequestDto.roles()))
                .build();
    }

    private Set<Role> determineRoles(Set<String> strRoles) throws RoleNotFoundException {
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            roles.add(roleFactory.getInstance("user"));
        } else {
            for (String role : strRoles) {
                roles.add(roleFactory.getInstance(role));
            }
        }
        return roles;
    }


    @Override
    public ResponseEntity<ApiResponseDto<?>> signInUser(SignInRequestDto signInRequestDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequestDto.email(), signInRequestDto.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsServiceImpl userDetailsService = (UserDetailsServiceImpl) authentication.getPrincipal();

        UserDetails userDetails = userDetailsService.loadUserByUsername(signInRequestDto.email());

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

/*        SignInResponseDto signInResponseDto = SignInResponseDto.builder()
                .email(userDetails.getUsername())
                .token(jw)
                .build();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.email(),
                        input.password()
                )
        );
        return userRepository.findByEmail(input.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    */
        return null;
    }
}
