package com.example.nyan.voiceassistant;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by NYAN on 24.04.2019.
 */

public class MessageView extends RecyclerView.ViewHolder {
    TextView messageText;
    TextView messageTime;

    public MessageView(View itemView){
        super(itemView);
        messageText = itemView.findViewById(R.id.textMessage);
        messageTime = itemView.findViewById(R.id.textTime);
    }

    public void bind(Message message){
        messageText.setText(message.text);

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        messageTime.setText(dateFormat.format(message.date));
    }
}
