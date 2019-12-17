package com.study.android.mytry.search;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.study.android.mytry.R;
import com.study.android.mytry.cash.Kakao_cash;
import com.study.android.mytry.challenge_public.CommentView;
import com.study.android.mytry.login.GlobalApplication;
import com.study.android.mytry.login.RequestHttpConnection;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

import static com.study.android.mytry.login.Intro.local_url;
import static com.study.android.mytry.main.Fragment_Search.bookuserid;

public class searchmain_singerAdapter extends BaseAdapter {
    private static final String TAG="lecture";
    Context serach_context;
    ArrayList<searchmain_SingerItem> items=new ArrayList<>();

    GlobalApplication myApp;

    public searchmain_singerAdapter(Context context){

        this.serach_context=context;
    }

    public void addItem(searchmain_SingerItem item){
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
    public View getView(final int position,final View convertView, ViewGroup parent){

        searchmainView view=null;

        if(convertView==null){
            view=new  searchmainView(serach_context);
        }else {
            view=(searchmainView) convertView;
        }

        final searchmain_SingerItem item=items.get(position);

        view.setChallenge_name(item.getChallenge_title());
        view.setChallenge_start("시작일 "+item.getChallenge_start());
        view.setChallenge_end("종료일 "+item.getChallenge_end());
        view.setChallenge_image(item.getChallenge_first_image());
        Log.d("testtxtx","사진이름"+item.getChallenge_first_image());



        //그래드 뷰 클릭
        view.setOnClickListener(new Button.OnClickListener() {
          public void onClick(View v){
              Log.d("testtxtx",item.getChallenge_num());
              item.getChallenge_num();

              Intent intent = new Intent(serach_context, Search_detail.class);

              intent.putExtra("challenge_num",item.getChallenge_num());
              intent.putExtra("challenge_title",item.getChallenge_title());
              intent.putExtra("challenge_category",item.getChallenge_category());
              intent.putExtra("challenge_type",item.getChallenge_type());
              intent.putExtra("challenge_frequency",item.getChallenge_frequency());
              intent.putExtra("challenge_start",item.getChallenge_start());
              intent.putExtra("challenge_end",item.getChallenge_end());
              intent.putExtra("challenge_fee",item.getChallenge_fee());
              intent.putExtra("challenge_time",item.getChallenge_time());
              intent.putExtra("challenge_detail",item.getChallenge_detail());
              intent.putExtra("challenge_first_image",item.getChallenge_first_image());
              intent.putExtra("challenge_state",item.getChallenge_state());
              intent.putExtra("challenge_public",item.getChallenge_public());
              intent.putExtra("challenge_exp",item.getChallenge_exp());
         //     intent.putExtra("challenge_date",item.getChallenge_date());
              intent.putExtra("challenge_host",item.getChallenge_host());

              serach_context.startActivity(intent);



            }
        });


        ImageButton bookmaker=view.findViewById(R.id.imageButtonstar);
        bookmaker.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                String str = "content: "+item.getChallenge_num();
                Log.d("bookmaker",str);
                String url = local_url+"/uzinee/bookmake";
                String msg = "?challenge_num="+item.getChallenge_num()+
                        "&member_id="+bookuserid;
               Log.d("lecture", msg);
                url = url +msg;
               Log.d("lecture", url);

                NetworkTask networkTask = new NetworkTask(url, null);
                networkTask.execute();

                Toast.makeText(serach_context,"북마크에 추가 되었습니다.", Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }
    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {
            Log.d("done_money1231", "통과통과4");
            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {
            String result; // 요청 결과를 저장할 변수.
            RequestHttpConnection requestHttpURLConnection = new RequestHttpConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.
            return result;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            try {
                if(s != null) {
                    JSONObject json = new JSONObject(s);


                }


            }   catch (JSONException e) {
                Log.d("eeeeeeettext", "");
                e.printStackTrace();
            }
        }
    }


}
