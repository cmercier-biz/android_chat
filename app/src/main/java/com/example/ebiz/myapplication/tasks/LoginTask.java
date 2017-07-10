package com.example.ebiz.myapplication.tasks;

import android.util.Log;

import com.example.ebiz.myapplication.model.LoginResponse;
import com.example.ebiz.myapplication.model.User;
import com.google.gson.Gson;

/**
 * Ask the server if the user can login.
 *
 * Created by ebiz on 07/07/2017.
 */
public class LoginTask extends AbstractFetchTask<Boolean> {

    private static final String LOGIN_URL = "https://training.loicortola.com/chat-rest/1.0/connect";

    public LoginTask(ITaskCallback<Boolean> taskCallback) {
        super(taskCallback);
    }

    @Override
    protected String getUrl() {
        return LOGIN_URL;
    }

    @Override
    protected Boolean parseJsonResponse(String response) {
        Gson gson = new Gson();
        LoginResponse loginResponse = gson.fromJson(response, LoginResponse.class);
        if (loginResponse == null) {
            Log.i(LoginTask.class.getSimpleName(), "Failed to parse to json.");
        }
        else {
            Log.i(LoginTask.class.getSimpleName(), "Successfully parse loginResponse response:");
            Log.i(LoginTask.class.getSimpleName(), loginResponse.toString());
        }
        return loginResponse != null && loginResponse.getStatus() == 200;
    }
}
