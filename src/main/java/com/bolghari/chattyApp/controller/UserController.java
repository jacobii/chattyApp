package com.bolghari.chattyApp.controller;

import com.bolghari.chattyApp.model.ChatMessage;
import com.bolghari.chattyApp.model.User;
import com.bolghari.chattyApp.service.ChatMessageService;
import com.bolghari.chattyApp.service.ChatRoomService;
import com.bolghari.chattyApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    @Autowired
    private ChatRoomService roomService;

    @Autowired
    private ChatMessageService messageService;

    @Autowired
    private UserService service;

    @GetMapping("/signup")
    public String registerUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("title", "Sign up");
        return "signup";
    }

    @PostMapping("/signup")
    public void saveUser(@ModelAttribute("user") User user) {

        service.createUser(user);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/myprofile")
    public String myProfile(Model model, Authentication authentication) {
        model.addAttribute("authentication", authentication != null ? authentication.getName() : "Not authorized");
        model.addAttribute("userDetails", service.getUser(authentication.getName()));
        model.addAttribute("title", "My profile");
        return "myprofile";
    }

    @GetMapping("/users")
    public String Users(Model model) {
        model.addAttribute("users", service.getUsers());
        model.addAttribute("title", "All Users");
        return "users";
    }

    @GetMapping("/rooms")
    public String chatRooms(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("title", "All chatrooms");
        return "rooms";
    }

    @GetMapping("/chat/{roomId}")
    public String getMessages(Model model, @PathVariable("roomId") String roomId, Authentication authentication) {
        ChatMessage messageContent = new ChatMessage();
        model.addAttribute("messageContent", messageContent);
        model.addAttribute("messages", messageService.findChatMessageByRoomId(roomId));
        model.addAttribute("roomId", roomId);
        model.addAttribute("roomName", roomService.getRoomName(roomId).get().getRoomName());
        model.addAttribute("room", roomService.getAllRooms());
        model.addAttribute("authName", authentication != null ? authentication.getName() : "Not authorized");
        return "chat";
    }

    @PostMapping("/message/{roomId}")
    public String createMessage(@ModelAttribute("messageContent") ChatMessage messageContent, @PathVariable("roomId") String roomId) {
        messageService.createMessage(messageContent);
        return "redirect:/chat/" + roomId + "#scrollToBottom";
    }


}
