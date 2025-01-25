package com.digidive.digidivebackend.service.impl;

import com.digidive.digidivebackend.entity.User;
import com.digidive.digidivebackend.exceptions.UserNotFoundException;
import com.digidive.digidivebackend.repository.UserRepository;
import com.digidive.digidivebackend.service.RoleService;
import com.digidive.digidivebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.digidive.digidivebackend.utils.Constants.ErrorMessages.USER_NOT_FOUND_WITH_EMAIL;
import static com.digidive.digidivebackend.utils.Constants.ErrorMessages.USER_NOT_FOUND_WITH_ID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Page<User> findAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return Optional.of(userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_EMAIL + email)));
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.of(userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID + id)));
    }

}
