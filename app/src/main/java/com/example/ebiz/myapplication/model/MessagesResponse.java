package com.example.ebiz.myapplication.model;

import java.util.List;

/**
 * Messages response
 *
 * Created by ebiz on 07/07/2017.
 */
public class MessagesResponse {

    private List<Message> messages;

    public MessagesResponse(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
