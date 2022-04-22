package com.bolghari.chattyApp.model;

import org.springframework.data.annotation.Id;

public class ChatRoom {
    @Id
    private String id;
    private String roomName;
    private boolean locked;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
