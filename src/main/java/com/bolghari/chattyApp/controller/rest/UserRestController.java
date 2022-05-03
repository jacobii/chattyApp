package com.bolghari.chattyApp.controller.rest;

import com.bolghari.chattyApp.model.User;
import com.bolghari.chattyApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @PreAuthorize("#username == principal.username or hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
        User user = service.getUser(username);
        if(user != null) {
            return new ResponseEntity<>(service.getUser(username), HttpStatus.OK);
        }else {
            return new ResponseEntity("User with username: %s not found"+ username, HttpStatus.OK);
        }
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("#id == principal.id || hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<String>  deleteUser(@PathVariable("id") String id) {
            Optional<User> exist = service.getUserById(id);

            if(!exist.isEmpty()) {
                service.deleteUser(id);
                return new ResponseEntity<>("Successfully deleted user: "+exist.get().getUsername(), HttpStatus.OK);
            }
            return new ResponseEntity("No user by the id found.."+id,HttpStatus.NOT_FOUND);
    }


    @PutMapping("/edit/{id}")
    @PreAuthorize("#id == principal.id || hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<String> updateUser(@RequestBody User user, @PathVariable("id") String id) {
        Optional<User> exist = service.getUserById(id);
        if(!exist.isEmpty()) {
            service.updateUser(user, id);
            return new ResponseEntity<>("Successfully edit user with id: "+id, HttpStatus.OK);
        }
        return new ResponseEntity<>("Error, could not find the user. Please check for typo", HttpStatus.BAD_REQUEST);
    }
}
