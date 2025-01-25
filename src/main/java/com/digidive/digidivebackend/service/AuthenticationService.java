package com.digidive.digidivebackend.service;

import com.digidive.digidivebackend.dto.LoginUserDto;
import com.digidive.digidivebackend.dto.RegisterUserDto;
import com.digidive.digidivebackend.entity.User;

public interface AuthenticationService {

    User signup(RegisterUserDto input);

    User authenticate(LoginUserDto input);
}
