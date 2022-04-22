package com.bolghari.chattyApp.service;

import com.bolghari.chattyApp.model.ChatMessage;
import com.bolghari.chattyApp.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {
    @Autowired
    ChatMessageRepository chatMsgRepo;

    public ChatMessage createMessage(ChatMessage message) {

        return chatMsgRepo.save(message);
    }

    public List<ChatMessage> getAllMessages() {
        return chatMsgRepo.findAll();
    }

    public List<ChatMessage> findChatMessageByRoomId(String id) {
        return chatMsgRepo.findChatMessageByRoomId(id);
    }
}
