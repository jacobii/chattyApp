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
import java.util.Optional;

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
    public  ResponseEntity<String> createRoom(@RequestBody ChatRoom room) {

        roomService.createChatRoom(room);
        return new ResponseEntity<>("Succesfully created the room: "+room.getRoomName(), HttpStatus.OK);
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
    @PreAuthorize("#username == principal.username or hasRole('ROLE_ADMIN')")
    @GetMapping("/messages/{username}")
    public List<ChatMessage> getMessagesFromUser(@PathVariable("username") String username) {
        return messageService.getMessagesByUser(username);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteall")
    public @ResponseBody
    ResponseEntity<String> deleteAllMessages() {
        List<ChatMessage> exist = messageService.getAllMessages();
        if(!exist.isEmpty()) {
            messageService.deleteAllMessages();
            return new ResponseEntity<>("Successfully deleted all messsage ", HttpStatus.OK);
        }
        return new ResponseEntity("No messages found..",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping ("/message/delete/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable("id") String id ) {
        Optional<ChatMessage> exist = messageService.getMessageById(id);
        if(!exist.isEmpty()) {
            messageService.deleteMessage(id);
            return new ResponseEntity<>("Successfully deleted messsage with id: "+id, HttpStatus.OK);
        }
        return new ResponseEntity("No message found..",HttpStatus.NOT_FOUND);
    }
}
