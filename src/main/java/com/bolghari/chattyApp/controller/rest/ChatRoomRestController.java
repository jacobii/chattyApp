package com.bolghari.chattyApp.controller.rest;

import com.bolghari.chattyApp.model.ChatMessage;
import com.bolghari.chattyApp.model.ChatRoom;
import com.bolghari.chattyApp.service.ChatMessageService;
import com.bolghari.chattyApp.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/chat")
public class ChatRoomRestController {

    @Autowired
    private ChatRoomService roomService;

    @Autowired
    private ChatMessageService messageService;

    // ROOMS
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public void createRoom(@RequestBody ChatRoom room) {
        roomService.createChatRoom(room);
    }


    @GetMapping("/rooms")
    public List<ChatRoom> getAllRooms() {
        return roomService.getAllRooms();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/room/{room}")
    public void deleteRoom(@PathVariable("room") String id) {
        roomService.deleteRoom(id);
    }

    // MESSAGES

    @PostMapping("/message")
    public void createMessage(@RequestBody ChatMessage message) {
        messageService.createMessage(message);
    }

    @GetMapping("/messages")
    public List<ChatMessage> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/room/{room}")
    public List<ChatMessage> getMessagesFromRoom(@PathVariable("room") String id) {
        return messageService.findChatMessageByRoomId(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteall")
    public @ResponseBody
    ResponseEntity<Void> deleteAllMessages() {
        HttpHeaders httpHeader = new HttpHeaders();
        messageService.deleteAllMessages();
        httpHeader.add("description", "all users have successfully been deleted");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeader).build();
    }
}
