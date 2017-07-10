package com.example.ebiz.myapplication.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ebiz.myapplication.model.Message;
import com.example.ebiz.myapplication.model.MessageRequest;
import com.example.ebiz.myapplication.model.User;
import com.example.ebiz.myapplication.utils.HttpRequestUtils;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ebiz on 10/07/2017.
 */
public class PostMessageTask extends AsyncTask<MessageRequest, Void, Boolean> {

    private static final String POST_MESSAGE_URL = "https://training.loicortola.com/chat-rest/1.0/messages";

    private ITaskCallback<Boolean> taskCallback;

    public PostMessageTask(ITaskCallback<Boolean> taskCallback) {
        this.taskCallback = taskCallback;
    }

    @Override
    protected Boolean doInBackground(MessageRequest... messageRequests) {
        Log.i(PostMessageTask.class.getSimpleName(), "Try to login to server...");
        HttpURLConnection connection = null;
        for (MessageRequest messageRequest : messageRequests) {
            try {
                URL loginUrl = new URL(POST_MESSAGE_URL + "/" + messageRequest.getUser().getUsername() + "/" + messageRequest.getUser().getPassword());
                connection = (HttpURLConnection) loginUrl.openConnection();
                connection.setRequestMethod("POST");

                String jsonMessage = toJson(messageRequest.getMessage());

                connection.setRequestProperty( "Content-Type", "application/json");
                connection.setRequestProperty( "charset", "utf-8");
                connection.setRequestProperty( "Content-Length", Integer.toString( jsonMessage.length() ));

                HttpRequestUtils.writeToStream(connection.getOutputStream(), jsonMessage);

                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    String response = HttpRequestUtils.streamToString(connection.getInputStream());
                    Log.i(PostMessageTask.class.getSimpleName(), response);
                    return Boolean.TRUE;
                }
            }
            catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
        Log.i(PostMessageTask.class.getSimpleName(), "Failure.");
        return null;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        taskCallback.callback(result);
    }

    private String toJson(Message message) {
        Gson gson = new Gson();
        return gson.toJson(message);
    }
}
