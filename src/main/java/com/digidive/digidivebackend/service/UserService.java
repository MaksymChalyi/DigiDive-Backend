package com.digidive.digidivebackend.service;


import com.digidive.digidivebackend.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<User> findAllUsers(int page, int size);
}
