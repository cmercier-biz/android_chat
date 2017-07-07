package com.example.ebiz.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ebiz.myapplication.R;
import com.example.ebiz.myapplication.adapters.MessageAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

        recyclerView = (RecyclerView) findViewById(R.id.messages_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // FIXME : get messages
        List<String> messages = new ArrayList<>();
        messages.add("Hello");
        messages.add("how are you ?");
        messages.add("fine");
        messages.add("I love android !");

        recyclerView.setAdapter(new MessageAdapter(messages));
    }
}
