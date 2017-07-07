package com.example.ebiz.myapplication.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ebiz.myapplication.utils.HttpRequestUtils;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ebiz on 07/07/2017.
 */

public class LoginTask extends AsyncTask<User, Void, Boolean> {

    private static final String LOGIN_URL = "https://training.loicortola.com/chat-rest/1.0/connect";

    private LoginCallback loginResponse;

    public LoginTask(LoginCallback onLoginResponse) {
        this.loginResponse = onLoginResponse;
    }

    @Override
    protected Boolean doInBackground(User... users) {
        Log.i(LoginTask.class.getSimpleName(), "Try to login to server...");
        HttpURLConnection connection = null;
        for (User user : users) {
            try {
                URL loginUrl = new URL(LOGIN_URL + "/" + user.getUsername() + "/" + user.getPassword());
                connection = (HttpURLConnection) loginUrl.openConnection();

                Log.i(LoginTask.class.getSimpleName(), "Get input stream...");
                InputStream inputStream = connection.getInputStream();

                Log.i(LoginTask.class.getSimpleName(), "Read response...");
                String response = HttpRequestUtils.streamToString(inputStream);
                Log.i(LoginTask.class.getSimpleName(), response);

                LoginStatus status = parseJsonResponse(response);
                return status.getStatus() == 200;
            } catch (java.io.IOException e) {
                Log.i(LoginTask.class.getSimpleName(), "==== Failure ====");
                e.printStackTrace();
            }
            finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }
        return Boolean.FALSE;
    }

    private LoginStatus parseJsonResponse(String response) {
        Gson gson = new Gson();
        LoginStatus status = gson.fromJson(response, LoginStatus.class);
        if (status == null) {
            Log.i(LoginTask.class.getSimpleName(), "Failed to parse to json.");
        }
        else {
            Log.i(LoginTask.class.getSimpleName(), "Successfully parse status response:");
            Log.i(LoginTask.class.getSimpleName(), status.toString());
        }
        return status;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        loginResponse.onLogin(result);
    }

    public interface LoginCallback {
        void onLogin(boolean canLogin);
    }
}
