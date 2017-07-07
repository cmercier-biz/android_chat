package com.example.ebiz.myapplication.tasks;

/**
 * Created by ebiz on 07/07/2017.
 */
public class Message {

    private String uuid;

    private String username;

    private String message;

    public Message(String uuid, String username, String message) {
        this.uuid = uuid;
        this.username = username;
        this.message = message;
    }

    public String getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }
}
