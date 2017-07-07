package com.example.ebiz.myapplication.tasks;

import java.util.List;

/**
 * Created by ebiz on 07/07/2017.
 */

public class MessageRequest {

    private User user;

    private List<Message> messages;

    public MessageRequest(User user, List<Message> messages) {
        this.user = user;
        this.messages = messages;
    }

    public User getUser() {
        return user;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
