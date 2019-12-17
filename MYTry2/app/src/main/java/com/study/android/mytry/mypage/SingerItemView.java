package com.study.android.mytry.mypage;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.android.mytry.R;

public class SingerItemView extends LinearLayout {

    TextView title;
    TextView content;
    TextView date;

     public SingerItemView(Context context){
        super(context);

        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.mypage_alert_item,this, true);


         title = (TextView) findViewById(R.id.alert_list_title);
         content = (TextView) findViewById(R.id.alert_list_content);
         date = (TextView) findViewById(R.id.alert_list_date);

    }

    public void setTitle(String str){
        title.setText(str);
    }
    public void setContent(String str){
        content.setText(str);
    }
    public void setDate(String str){
        date.setText(str);
    }

}