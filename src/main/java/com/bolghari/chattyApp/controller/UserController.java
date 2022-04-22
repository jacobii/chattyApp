package com.bolghari.chattyApp.controller;

import com.bolghari.chattyApp.model.User;
import com.bolghari.chattyApp.security.UserRole;
import com.bolghari.chattyApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/signup")
    public String registerUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("title", "Sign up");
        return "signup";
    }

    @PostMapping("/createUser")
    public void saveUser(@ModelAttribute("user") User user){
        service.createUser(user);
    }

    @GetMapping("/myprofile")
    public String myProfile(Authentication authentication) {
        if (authentication != null)
            return authentication.getName();
        else
            return "Not authorized";
    }

}
