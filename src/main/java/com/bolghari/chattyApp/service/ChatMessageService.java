package com.bolghari.chattyApp.service;

import com.bolghari.chattyApp.model.ChatMessage;
import com.bolghari.chattyApp.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageService {
    @Autowired
    ChatMessageRepository chatMsgRepo;

    public ChatMessage createMessage(ChatMessage messageContent) {
        Date date = new Date();
        messageContent.setCreatedAt(date);
        return chatMsgRepo.save(messageContent);
    }

    public void deleteAllMessages() {
        chatMsgRepo.deleteAll();
    }

    public List<ChatMessage> getAllMessages() {
        return chatMsgRepo.findAll();
    }

    public Optional<ChatMessage> getMessageById(String id) {
        return chatMsgRepo.findById(id);
    }

    public List<ChatMessage> findChatMessageByRoomId(String id) {
        return chatMsgRepo.findChatMessageByRoomId(id);
    }

    public List<ChatMessage> getMessagesByUser(String username) {
        return chatMsgRepo.findAllMessagesByUser(username);
    }

    public void deleteMessage(String id) {
        Optional<ChatMessage> msg = chatMsgRepo.findById(id);
        if (msg != null) {
            chatMsgRepo.deleteById(id);
        }
    }
}
