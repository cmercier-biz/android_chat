package com.example.ebiz.myapplication.model;

/**
 * Created by ebiz on 07/07/2017.
 */
public class Message {

    private String uuid;

    private String login;

    private String message;

    public Message(String uuid, String login, String message) {
        this.uuid = uuid;
        this.login = login;
        this.message = message;
    }

    public String getUuid() {
        return uuid;
    }

    public String getLogin() {
        return login;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Message message = (Message) o;
        return uuid != null ? uuid.equals(message.uuid) : message.uuid == null;
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Message{" +
                "uuid='" + uuid + '\'' +
                ", login='" + login + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
