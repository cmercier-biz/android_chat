package com.example.ebiz.myapplication.model;

import java.util.List;

/**
 * Login response.
 *
 * Created by ebiz on 07/07/2017.
 */
public class LoginResponse {

    private int status;

    private String message;

    private List<String> elements;

    public LoginResponse(int status, String message, List<String> elements) {
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
        return "LoginResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", elements=" + elements +
                '}';
    }
}
