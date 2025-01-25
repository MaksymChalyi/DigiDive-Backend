package com.digidive.digidivebackend.entity;

import com.digidive.digidivebackend.exceptions.RoleNotFoundException;
import com.digidive.digidivebackend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleFactory {

    private final RoleRepository roleRepository;

    public Role getInstance(String role) {
        switch (role) {
            case "admin" -> {
                return roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new RoleNotFoundException("Role not found"));
            }
            case "user" -> {
                return roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RoleNotFoundException("Role not found"));
            }
            default -> throw new RoleNotFoundException("No role found for " + role);
        }
    }

}
