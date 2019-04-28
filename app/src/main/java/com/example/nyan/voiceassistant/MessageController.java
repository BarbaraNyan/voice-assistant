package com.example.nyan.voiceassistant;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NYAN on 24.04.2019.
 */

public class MessageController extends RecyclerView.Adapter {

    public List<Message> messageList = new ArrayList<>();

    private static final int USER_MESSAGE = 0;
    private static final int ASSISTANT_MESSAGE = 1;

    //какой тип сообщений хранится в position
    @Override
    public int getItemViewType(int position) {
        Message message = messageList.get(position);

        return message.isSent ? USER_MESSAGE:ASSISTANT_MESSAGE;
    }

    //как сообщения выглядят
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int layout;
        if(viewType == USER_MESSAGE){
            layout = R.layout.user_message;
        }
        else {
            layout = R.layout.assistant_message;
        }
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(layout,viewGroup,false);
        return new MessageView(view);
    }

    //что за данные там лежат
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message message = messageList.get(position);
        ((MessageView)holder).bind(message);
    }

    //сколько
    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
