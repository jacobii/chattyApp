package com.bolghari.chattyApp.controller.rest;

import com.bolghari.chattyApp.model.User;
import com.bolghari.chattyApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private UserService service;


    @PostMapping("/create")
    public void createUser(@RequestBody User user) {
        service.createUser(user);
    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return service.getUsers();
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("#username == principal.username")
    public User getUser(@PathVariable("username") String username) {
            return service.getUser(username);

    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("#id == principal.id")
    public void deleteUser(@PathVariable("id") String id) {

        service.deleteUser(id);
    }


    @PutMapping("/edit")
    public void updateUser() {

    }
}
