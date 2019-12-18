package com.study.android.mytry.chat;

import android.widget.ImageView;
import android.widget.TextView;

public class chat_room_singerItem {

    private String chat_room_image;
    private String chat_room_title;
    private String chat_room_previous;
    private String chat_room_previous_time;
    private String chat_room_count;
    private String chat_room_participant_count;

    public chat_room_singerItem (String chat_room_image, String chat_room_title, String chat_room_previous, String chat_room_previous_time, String chat_room_count, String chat_room_participant_count) {
        this.chat_room_image = chat_room_image;
        this.chat_room_title = chat_room_title;
        this.chat_room_previous = chat_room_previous;
        this.chat_room_previous_time = chat_room_previous_time;
        this.chat_room_count = chat_room_count;
        this.chat_room_participant_count = chat_room_participant_count;
    }

    public String getChat_room_image() {
        return chat_room_image;
    }

    public String getChat_room_title() {
        return chat_room_title;
    }

    public String getChat_room_previous() {
        return chat_room_previous;
    }

    public String getChat_room_previous_time() {
        return chat_room_previous_time;
    }

    public String getChat_room_count() {
        return chat_room_count;
    }
    public String getChat_room_participant_count() {
        return chat_room_participant_count;
    }

    public void setChat_room_image(String chat_room_image) {
        this.chat_room_image = chat_room_image;
    }

    public void setChat_room_title(String chat_room_title) {
        this.chat_room_title = chat_room_title;
    }

    public void setChat_room_previous(String chat_room_previous) {
        this.chat_room_previous = chat_room_previous;
    }

    public void setChat_room_previous_time(String chat_room_previous_time) {
        this.chat_room_previous_time = chat_room_previous_time;
    }

    public void setChat_room_count(String chat_room_count) {
        this.chat_room_count = chat_room_count;
    }

    public void setChat_room_participant_count(String chat_room_participant_count) {
        this.chat_room_participant_count = chat_room_participant_count;
    }
}
