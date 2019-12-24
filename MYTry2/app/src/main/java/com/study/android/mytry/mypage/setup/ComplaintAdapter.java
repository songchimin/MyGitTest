package com.study.android.mytry.mypage.setup;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.study.android.mytry.R;
import java.util.ArrayList;

public class ComplaintAdapter extends BaseAdapter {
    private static final String TAG = "lecture";

    Context context;
    ArrayList<ComplaintItem> items = new ArrayList<>();

    ListView listView;

    public ComplaintAdapter(Context context) {

        this.context = context;
    }

    public void addItem( ComplaintItem item) {
        items.add(item);
        System.out.println(item);
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

        ComplaintItemView view = null;

        if (convertView == null) {
            view = new ComplaintItemView(context);
        } else {
            view = (ComplaintItemView) convertView;
        }

        final ComplaintItem item = items.get(position);
        view.setComment_title(String.valueOf(item.getComment_num()));
        view.setState(item.getComplaint_state());
        view.setDate(item.getComplaint_date());

        // 좋아요 버튼 처리

        return view;
    }

}
