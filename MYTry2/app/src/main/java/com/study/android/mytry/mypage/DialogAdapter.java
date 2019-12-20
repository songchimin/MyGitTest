package com.study.android.mytry.mypage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;


public class DialogAdapter extends BaseAdapter {
    private static final String TAG = "lecture";

    Context context;
    ArrayList<DialogItem> items = new ArrayList<>();
    LinearLayout linearLayout;

    HashMap<String, String> map = new HashMap<>();


    public DialogAdapter(Context context) {
        this.context = context;
    }

    public void addItem(DialogItem item) {
        items.add(item);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DialogItemView view = null;

        if (convertView == null) {
            view = new DialogItemView(context);
        } else {
            view = (DialogItemView) convertView;
        }

        final DialogItem item = items.get(position);
        view.setTitle(item.getTitle());
        view.setContent(item.getContent());
        view.setDate(item.getDate());

          return view;
    }


}
