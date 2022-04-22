package com.bolghari.chattyApp.repository;

import com.bolghari.chattyApp.model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
}
