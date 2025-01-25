package com.digidive.digidivebackend.service;


import com.digidive.digidivebackend.entity.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {
    boolean existByEmail(String email);

    void save(User user);

    Page<User> findAllUsers(int page, int size);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(Long id);
}
