package com.bolghari.chatapp.model;

import org.springframework.data.annotation.Id;

public class Message {


    @Id
    private String id;
    private String fromUser;
    private String toUser;
    private String messageContent;
    private String time;

}
