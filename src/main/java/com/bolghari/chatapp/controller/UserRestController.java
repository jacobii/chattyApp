package com.bolghari.chatapp.controller;

import com.bolghari.chatapp.model.User;
import com.bolghari.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
    @Autowired
    private UserService service;


    @PostMapping("/create")
    public void createUser(@RequestBody User user) {
        service.createUser(user);
    }
}
