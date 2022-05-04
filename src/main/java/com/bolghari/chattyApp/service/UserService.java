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

    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
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

    public void updateUserRole(User userToEdit, String id) {
       User user = userRepo.getById(id);
        if (user != null) {
            user.setRole(userToEdit.getRole() == null ? user.getRole() : userToEdit.getRole());
            userRepo.save(user);
        }
    }

    public void updateUser(User userToEdit, String id) {
        User user = userRepo.getById(id);
        if (user != null) {
            if (!(userToEdit.getProfileImg() == null || userToEdit.getProfileImg().equals(""))) user.setProfileImg(userToEdit.getProfileImg());
            if (!(userToEdit.getName() == null || userToEdit.getName().equals(""))) user.setName(userToEdit.getName());
            if (!(userToEdit.getEmail() == null || userToEdit.getEmail().equals(""))) user.setEmail(userToEdit.getEmail());
            if (!(userToEdit.getAge() <= 0)) user.setAge(userToEdit.getAge());
            if (!(userToEdit.getPassword() == null || userToEdit.getPassword().equals(""))) user.setPassword(passwordEncoder.encode(userToEdit.getPassword()));
                userRepo.save(user);
        }
    }

}
