package com.example.nyan.voiceassistant;

import java.util.Date;

/**
 * Created by NYAN on 24.04.2019.
 */

public class Message {
    public String text;
    public Date date;
    public boolean isSent;

    public Message(String text, boolean isSent) {
        this.text = text;
        this.date = new Date();
        this.isSent = isSent;
    }
}
