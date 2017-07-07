package com.example.ebiz.myapplication.tasks;

import java.util.List;

/**
 * Created by ebiz on 07/07/2017.
 */

public class LoginStatus {
    private int status;

    private String message;

    private List<String> elements;

    public LoginStatus(int status, String message, List<String> elements) {
        this.status = status;
        this.message = message;
        this.elements = elements;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return "LoginStatus{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", elements=" + elements +
                '}';
    }
}
