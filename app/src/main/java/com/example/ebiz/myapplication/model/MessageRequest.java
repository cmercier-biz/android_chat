package com.example.ebiz.myapplication.model;

/**
 * Created by ebiz on 10/07/2017.
 */
public class MessageRequest {

    private User user;

    private Message message;

    public MessageRequest(User user, Message message) {
        this.user = user;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public Message getMessage() {
        return message;
    }
}
