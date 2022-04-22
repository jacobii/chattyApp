package com.bolghari.chattyApp.service;

import com.bolghari.chattyApp.model.User;
import com.bolghari.chattyApp.repository.UserRepository;
import com.bolghari.chattyApp.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;


    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }



    @Autowired
    UserRepository userRepo;


    public User createUser(User user) {
        System.out.println(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user);
        user.setRole(UserRole.USER);
        return userRepo.save(user);
    }



    public List<User> getUsers() {
        //return userRepo.findAll();
        return userRepo.findAll();
    }

    public User getUser(String username) {
       User user = userRepo.findUsersByName(username);
        System.out.println(user);
       if(user != null) {
           return user;
       }
    return null;
    }
    public void deleteUser(String id) {
       Optional<User> user = userRepo.findById(id);
       if (user != null) {
        userRepo.deleteById(id);
       }
    }

}
