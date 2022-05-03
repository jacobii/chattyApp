package com.bolghari.chattyApp.repository;

import com.bolghari.chattyApp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);

    @Query(value = "{ 'username' : ?0 }")
   User findUsersByName(String username);
    @Query(value = "{ 'id' : ?0 }")
    User getById(String id);

}
