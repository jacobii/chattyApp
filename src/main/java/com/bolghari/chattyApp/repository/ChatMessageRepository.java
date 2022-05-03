package com.bolghari.chattyApp.repository;

import com.bolghari.chattyApp.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String>  {

    @Query(value = "{ 'roomId' : ?0 }")
    List<ChatMessage> findChatMessageByRoomId(String id);

    @Query(value = "{'username' : ?0 }")
    List<ChatMessage> findAllMessagesByUser(String username);
}
