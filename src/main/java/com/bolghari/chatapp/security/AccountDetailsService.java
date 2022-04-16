package com.bolghari.chatapp.security;

import com.bolghari.chatapp.model.User;
import com.bolghari.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class AccountDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = repository.findByUsername(username);

        if(user ==null) {
            throw new UsernameNotFoundException("User was not found");
        }
        return new AccountDetails(user);
    }
}