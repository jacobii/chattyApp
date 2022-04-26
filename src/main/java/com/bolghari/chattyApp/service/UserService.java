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
        if (checkIfUserExist(user)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(UserRole.USER);
            return userRepo.save(user);
        } else {
            return null;
        }
    }

    public boolean checkIfUserExist(User user) {
        User notExist = userRepo.findByUsername(user.getUsername());
        if (notExist == null) {
            return true;
        } else {
            return false;
        }
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public User getUser(String username) {
        User user = userRepo.findUsersByName(username);
        System.out.println(user);
        if (user != null) {
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
