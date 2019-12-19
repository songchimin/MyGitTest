package com.study.android.mytry.chat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.study.android.mytry.R;

import java.util.ArrayList;

public class chat_content_singerAdapter extends BaseAdapter {

    private static final String TAG="lecture";
    Context context;

    private LayoutInflater _inflater;
    private ArrayList list;
    private int _layout;



    public chat_content_singerAdapter(Context context, int layout, ArrayList list){
        _inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
        _layout = layout;
    }


    @Override
    public int getCount(){
//        return items.size();
        return list.size();
    }

    @Override
    public Object getItem(int position){
        return "";
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    //머여

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

//        chat_contentView view = null;

        if(convertView==null){
            convertView = _inflater.inflate(_layout, parent,false);
        }

        ChatDTO chatDTO = (ChatDTO)list.get(position);
//
//        view.setChat_content(message);
//        view.setChat_content_time(time);

//

        TextView content = convertView.findViewById(R.id.chat_content);
        TextView time = convertView.findViewById(R.id.chat_content_time);
        TextView name = convertView.findViewById(R.id.chat_content_name);

        content.setText(chatDTO.getMessage());
        time.setText(chatDTO.getTime());
        name.setText(chatDTO.getUserName());

        return convertView;
    }
}
