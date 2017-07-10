package com.example.ebiz.myapplication.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ebiz.myapplication.model.LoginResponse;
import com.example.ebiz.myapplication.model.Message;
import com.example.ebiz.myapplication.model.MessagesResponse;
import com.example.ebiz.myapplication.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Try to get all messages.
 *
 * Created by ebiz on 07/07/2017.
 */
public class FetchMessagesTask extends AbstractFetchTask<MessagesResponse> {

    private static final String MESSAGES_URL = "https://training.loicortola.com/chat-rest/1.0/messages";

    public FetchMessagesTask(ITaskCallback<MessagesResponse> taskCallback) {
        super(taskCallback);
    }

    @Override
    protected String getUrl() {
        return MESSAGES_URL;
    }

    @Override
    protected MessagesResponse parseJsonResponse(String response) {
        Gson gson = new Gson();
        List<Message> messages = gson.fromJson(response, new TypeToken<List<Message>>(){}.getType());
        MessagesResponse messagesResponse = new MessagesResponse(messages);
        if (messagesResponse == null) {
            Log.i(FetchMessagesTask.class.getSimpleName(), "Failed to parse to json.");
        }
        else {
            Log.i(FetchMessagesTask.class.getSimpleName(), "Successfully parse status response:");
            Log.i(FetchMessagesTask.class.getSimpleName(), messagesResponse.toString());
        }
        return messagesResponse;
    }
}
