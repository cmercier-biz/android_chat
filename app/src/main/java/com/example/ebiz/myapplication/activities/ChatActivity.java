package com.example.ebiz.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ebiz.myapplication.R;
import com.example.ebiz.myapplication.adapters.MessageAdapter;
import com.example.ebiz.myapplication.model.Message;
import com.example.ebiz.myapplication.model.MessageRequest;
import com.example.ebiz.myapplication.model.MessagesResponse;
import com.example.ebiz.myapplication.model.User;
import com.example.ebiz.myapplication.tasks.FetchMessagesTask;
import com.example.ebiz.myapplication.tasks.ITaskCallback;
import com.example.ebiz.myapplication.tasks.PostMessageTask;

import java.util.UUID;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private EditText messageInput;

    private Button sendMessageButton;

    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // === Fetch messages ===
        setContentView(R.layout.chat_activity);

        recyclerView = (RecyclerView) findViewById(R.id.messages_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        messageAdapter = new MessageAdapter();
        recyclerView.setAdapter(messageAdapter);

        final User user = new User(getIntent().getStringExtra(User.USERNAME_FIELD),
                getIntent().getStringExtra(User.PASSWORD_FIELD));

        fetchMessages(user);

        // === Handle send messages ===
        messageInput = (EditText) findViewById(R.id.message_input);
        sendMessageButton = (Button) findViewById(R.id.message_send_button);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message(UUID.randomUUID().toString(), user.getUsername(), messageInput.getText().toString());
                MessageRequest messageRequest = new MessageRequest(user, message);

                new PostMessageTask(new ITaskCallback<Boolean>() {
                    @Override
                    public void callback(Boolean result) {
                        fetchMessages(user);
                    }
                }).execute(messageRequest);
            }
        });
    }

    private void fetchMessages(User user) {
        new FetchMessagesTask(new ITaskCallback<MessagesResponse>() {
            @Override
            public void callback(MessagesResponse result) {
                messageAdapter.setMessagesResponse(result);
            }
        }).execute(user);
    }
}
