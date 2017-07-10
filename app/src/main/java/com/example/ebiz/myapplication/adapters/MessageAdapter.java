package com.example.ebiz.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ebiz.myapplication.R;
import com.example.ebiz.myapplication.model.Message;
import com.example.ebiz.myapplication.model.MessagesResponse;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ebiz on 07/07/2017.
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private MessagesResponse messagesResponse;

    public MessageAdapter() {
        List<Message> messages = new ArrayList<>();
        messagesResponse = new MessagesResponse(messages);
    }

    public void setMessagesResponse(MessagesResponse messagesResponse) {
        this.messagesResponse = messagesResponse;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View messageView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message, parent, false);
        return new ViewHolder(messageView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getUsernameView().setText(messagesResponse.getMessages().get(position).getLogin() + " :");
        holder.getMessageView().setText(messagesResponse.getMessages().get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return messagesResponse == null ? 0 : messagesResponse.getMessages().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView usernameView;

        private TextView messageView;

        public ViewHolder(View view) {
            super(view);
            this.usernameView = (TextView) view.findViewById(R.id.message_username_text);
            this.messageView = (TextView) view.findViewById(R.id.message_text);
        }

        public TextView getUsernameView() {
            return usernameView;
        }

        public TextView getMessageView() {
            return messageView;
        }
    }
}
