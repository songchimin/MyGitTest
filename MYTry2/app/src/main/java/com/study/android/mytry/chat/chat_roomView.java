package com.study.android.mytry.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.android.mytry.R;

public class chat_roomView extends LinearLayout{

    ImageView chat_room_image;
    TextView chat_room_title;
    TextView chat_room_previous;
    TextView chat_room_previous_time;
    TextView chat_room_count;
    TextView chat_room_participant_count;

    public chat_roomView(Context context){
        super(context);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.chat_room_item_view,this, true);

        chat_room_image =  findViewById(R.id.chat_room_image);
        chat_room_title =  findViewById(R.id.chat_room_title);
        chat_room_previous =  findViewById(R.id.chat_room_previous);
        chat_room_previous_time =  findViewById(R.id.chat_room_previous_time);
        chat_room_count =  findViewById(R.id.chat_room_count);
        chat_room_participant_count =  findViewById(R.id.chat_room_participant_count);

    }


    public void setChat_room_image(ImageView chat_room_image) {

    }

    public void setChat_room_title(String chat_room_title) {

    }

    public void setChat_room_previous(String chat_room_previous) {

    }

    public void setChat_room_previous_time(String chat_room_previous_time) {

    }

    public void setChat_room_count(String chat_room_count) {

    }

    public void setChat_room_participant_count(String chat_room_participant_count) {

    }
}
