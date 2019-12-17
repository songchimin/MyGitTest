package com.study.android.mytry.main;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.formats.NativeAd;
import com.study.android.mytry.R;
import com.study.android.mytry.challenge_public.FileLoadConnection;
import com.study.android.mytry.login.GlobalApplication;
import com.study.android.mytry.login.RequestHttpConnection;
import com.study.android.mytry.search.search_SingerItem;

import com.study.android.mytry.search.searchmain_SingerItem;
import com.study.android.mytry.search.searchmain_singerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.study.android.mytry.login.Intro.local_url;

public class Fragment_Search extends Fragment {
    private static final String TAG = "lecture";
    private FloatingActionButton fab;
    GridView gridView;
    searchmain_singerAdapter adapter;
    TextView challenge_name;
    TextView challenge_start;
    TextView challenge_end ;
    NativeAd.Image image;
    ImageView imageView;
    Button all;
    Button hobby;
    Button life;
    Button capability;
    Button health;
    Button realtionship;
    Button asset;
    public static String bookuserid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        GlobalApplication myApp = (GlobalApplication) getActivity().getApplication();
        bookuserid = myApp.getGlobalString();
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.search_main, container, false);

        gridView = rootView.findViewById(R.id.search_list2);
//        adapter = new searchmain_singerAdapter(getActivity());
//

        all = rootView.findViewById(R.id.search_main_category_all);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = local_url + "/uzinee/serach_public_list";
                Log.d("done_money1231", url);
                NetworkTask networkTask = new NetworkTask(url, null);
                networkTask.execute();
            }
        });

        life = rootView.findViewById(R.id.search_main_category_life);
        life.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = local_url + "/uzinee/category_life";
                Log.d("done_money1231", url);
                NetworkTask networkTask = new NetworkTask(url, null);
                networkTask.execute();
            }
        });

        hobby = rootView.findViewById(R.id.search_main_category_hobby);
        hobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = local_url + "/uzinee/category_hobby";
                Log.d("done_money1231", url);
                NetworkTask networkTask = new NetworkTask(url, null);
                networkTask.execute();
            }
        });


        capability = rootView.findViewById(R.id.search_main_category_capability);
        capability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = local_url + "/uzinee/category_capability";
                Log.d("done_money1231", url);
                NetworkTask networkTask = new NetworkTask(url, null);
                networkTask.execute();
            }
        });


        health = rootView.findViewById(R.id.search_main_category_health);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = local_url + "/uzinee/category_health";
                Log.d("done_money1231", url);
                NetworkTask networkTask = new NetworkTask(url, null);
                networkTask.execute();
            }
        });


        realtionship = rootView.findViewById(R.id.search_main_category_relationship);
        realtionship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = local_url + "/uzinee/category_relationship";
                Log.d("done_money1231", url);
                NetworkTask networkTask = new NetworkTask(url, null);
                networkTask.execute();
            }
        });


        asset = rootView.findViewById(R.id.search_main_category_asset);
        asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = local_url + "/uzinee/category_asset";
                Log.d("done_money1231", url);
                NetworkTask networkTask = new NetworkTask(url, null);
                networkTask.execute();
            }
        });


        String url = local_url + "/uzinee/serach_public_list";
        Log.d("done_money1231", url);
        NetworkTask networkTask = new NetworkTask(url, null);
        networkTask.execute();



        return rootView;
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
        protected void onPostExecute(String json) {

            super.onPostExecute(json);

            try {
                if (json != null) {
                    JSONObject jsonObject = new JSONObject(json);
                    Log.d("ssssssettext2", "들어옴");
                    Log.d("ssssssettext2", json);
                    JSONArray ListArray = jsonObject.getJSONArray("search_list");

                    searchmain_SingerItem[] singermainItems = new searchmain_SingerItem[ListArray.length()];
                    adapter = new searchmain_singerAdapter(getActivity());
                    for (int i = 0; i < ListArray.length(); i++) {

                        JSONObject ListObject = ListArray.getJSONObject(i);

                        String url = local_url+"/" + ListObject.getString("challenge_first_image")+".jpg";

                        Log.d("testtxtx",url);
                        FileUploadNetworkTask fileUploadNetworkTask = new FileUploadNetworkTask(url, null);
                        fileUploadNetworkTask.execute();


                        singermainItems[i] =
                                new searchmain_SingerItem(
                                        String.valueOf(ListObject.getInt("challenge_num")),
                                        ListObject.getString("challenge_title"),
                                        ListObject.getString("challenge_category"),
                                        ListObject.getString("challenge_type"),
                                        ListObject.getString("challenge_frequency"),
                                        ListObject.getString("challenge_start"),
                                        ListObject.getString("challenge_end"),
                                        String.valueOf(ListObject.getInt("challenge_fee")),
                                        ListObject.getString("challenge_time"),
                                        ListObject.getString("challenge_detail"),
                                        ListObject.getString("challenge_first_image"),
                                        ListObject.getString("challenge_state"),
                                        ListObject.getString("challenge_public"),
                                        String.valueOf(ListObject.getInt("challenge_exp")),
                                        //      ListObject.getString("challenge_date"),
                                        ListObject.getString("challenge_host"));
                        adapter.addItem(singermainItems[i]);
                        Log.d("ssssssettext2", "들어옴111");


                    }
                    gridView.setAdapter(adapter);
                    Log.d("ssssssettext2", "들어옴222");
                } else {

                    challenge_name.setText("");
                    challenge_start.setText("");
                    challenge_end.setText("");

                    Log.d("ssssssettext2", "null임");

                }

            } catch (JSONException e) {
                Log.d("eeeeeeettext", "");
                e.printStackTrace();
            }
        }
    }


    public class FileUploadNetworkTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ContentValues values;

        public FileUploadNetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {

            Bitmap result; // 요청 결과를 저장할 변수.
            FileLoadConnection requestHttpURLConnection = new FileLoadConnection();
            result = requestHttpURLConnection.request(url); // 해당 URL로 부터 결과물을 얻어온다.

            Log.d("testtxtx","resilt   "+result.toString());

            return result;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap1) {

            super.onPostExecute(bitmap1);
            Log.d("testtxtx","bitmap"+bitmap1.toString());
        }
    }

}
