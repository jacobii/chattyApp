package com.bolghari.chatapp.service;

import com.bolghari.chatapp.model.User;
import com.bolghari.chatapp.repository.UserRepository;
import com.bolghari.chatapp.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;


    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User encodedPassword(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return user;
    }

    @Autowired
    UserRepository userRepo;


    public User createUser(User user) {
        //user = encodedPassword(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.USER);
        return userRepo.save(user);
    }
}
