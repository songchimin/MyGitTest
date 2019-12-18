package com.study.android.mytry.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.study.android.mytry.R;
import com.study.android.mytry.search.search_SingerItem;

public class chat_room extends AppCompatActivity {
    Button chat_room_back_btn;
    ListView chat_room_list;

    chat_room_singerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        chat_room_back_btn = findViewById(R.id.chat_room_back_btn);
        chat_room_list = findViewById(R.id.chat_room_list);

        adapter = new chat_room_singerAdapter(chat_room.this);

        chat_room_singerItem[] items = new chat_room_singerItem[1];

        items[0] = new chat_room_singerItem("","일찍자기","최근 채팅 내용","최근채팅시간","안읽은갯수","참가자수");

        adapter.addItem(items[0]);

        chat_room_list.setAdapter(adapter);
    }
}
