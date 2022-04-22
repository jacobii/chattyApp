package com.bolghari.chattyApp.service;

import com.bolghari.chattyApp.model.ChatRoom;
import com.bolghari.chattyApp.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService {



    @Autowired
    ChatRoomRepository chatRoomRepo;

    public ChatRoom createChatRoom(ChatRoom room) {
        return chatRoomRepo.save(room);
    }

    public List<ChatRoom> getAllRooms() {
        return chatRoomRepo.findAll();
    }

    public void deleteRoom(String id) {
        chatRoomRepo.deleteById(id);
    }
}
