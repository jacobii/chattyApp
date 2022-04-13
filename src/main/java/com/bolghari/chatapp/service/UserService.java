package com.bolghari.chatapp.service;

import com.bolghari.chatapp.model.User;
import com.bolghari.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;


    public User createUser(User user) {
        return userRepo.save(user);
    }
}
