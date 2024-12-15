package com.digidive.digidivebackend.controller.user;

import com.digidive.digidivebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser(Principal principal) {
        return ResponseEntity.ok(principal);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUser(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        var users = userService.findAllUsers(page, size);
        return ResponseEntity.ok(users);
    }


}
