package com.study.android.mytry.chat;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.ArrayList;

public class chat_room_singerAdapter extends BaseAdapter {

    private static final String TAG="lecture";

    Context context;

    ArrayList<chat_room_singerItem> items = new ArrayList<>();

    public chat_room_singerAdapter(Context context){

        this.context = context;
    }

    public void addItem(chat_room_singerItem item){
        items.add(item);
    }

    @Override
    public int getCount(){
        return items.size();
    }

    @Override
    public Object getItem(int position){
        return items.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent){

        chat_roomView view = null;

        if(convertView==null){
            view = new chat_roomView(context);
        }else {
            view = (chat_roomView) convertView;
        }

        final chat_room_singerItem item = items.get(position);


//        view.setFilter_items(item.gtgetInterest_context());
//        view.setChat_room_image(item.getChat_room_image());
        view.setChat_room_title(item.getChat_room_title());
        view.setChat_room_previous(item.getChat_room_previous());
        view.setChat_room_previous_time(item.getChat_room_previous_time());
        view.setChat_room_count(item.getChat_room_count());

//        view.setOnClickListener(new Button.OnClickListener() {
//            public void onClick(View v){
//                Intent intent = new Intent(filter_context, search_filer_main.class);
//                intent.putExtra("sendData", item.getInterest_context());// 이 메서드를 통해 데이터를 전달합니다.
//
//
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                filter_context.startActivity(intent);
//
//            }
//        });

        return view;
    }
}
