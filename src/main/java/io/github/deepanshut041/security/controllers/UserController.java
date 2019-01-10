package io.github.deepanshut041.security.controllers;

import io.github.deepanshut041.security.entities.User;
import io.github.deepanshut041.security.entities.intefaces.CurrentUser;
import io.github.deepanshut041.security.exception.ResourceNotFoundException;
import io.github.deepanshut041.security.oauth2.UserPrincipal;
import io.github.deepanshut041.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}