package com.digidive.digidivebackend.service.impl;

import com.digidive.digidivebackend.dto.request.UserRequestDTO;
import com.digidive.digidivebackend.entity.User;
import com.digidive.digidivebackend.exceptions.EmployeeNotFoundException;
import com.digidive.digidivebackend.repository.UserRepository;
import com.digidive.digidivebackend.service.RoleService;
import com.digidive.digidivebackend.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.digidive.digidivebackend.utils.Constants.ErrorMessages.USER_NOT_FOUND_WITH_EMAIL;
import static com.digidive.digidivebackend.utils.Constants.ErrorMessages.USER_NOT_FOUND_WITH_ID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public Page<User> findAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userRepository.findAll(pageable);
        return users;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return Optional.of(userRepository.findByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundException(USER_NOT_FOUND_WITH_EMAIL + email)));
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.of(userRepository.findUserById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(USER_NOT_FOUND_WITH_ID + id)));
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_WITH_EMAIL + username));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().getName()))
        );
    }

    public void createNewUser(UserRequestDTO user) {

    }
}
