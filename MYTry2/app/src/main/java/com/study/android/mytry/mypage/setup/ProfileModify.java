package com.study.android.mytry.mypage.setup;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.android.mytry.R;
import com.study.android.mytry.challenge_public.FileLoadConnection;
import com.study.android.mytry.login.GlobalApplication;
import com.study.android.mytry.login.RequestHttpConnection;
import com.study.android.mytry.main.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.study.android.mytry.login.Intro.local_url;

public class ProfileModify extends Fragment implements MainActivity.onKeyBackPressedListener {

    LinearLayout into_pw;
    LinearLayout into_account;

    ImageView profileImg;

    TextView nickname;
    TextView introduction;
    TextView name;
    TextView id;
    TextView password;
    TextView account;

    String userid;


    HashMap<String, String> setup_map = new HashMap<>();

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.mypage_setup_profile, container, false);

        GlobalApplication myApp = (GlobalApplication) getActivity().getApplication();
        userid = myApp.getGlobalString();

        into_pw = rootView.findViewById(R.id.setup_into_pw);
        into_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PasswordModify.class);
                intent.putExtra("hashmap", setup_map);
                startActivity(intent);
            }
        });

        into_account = rootView.findViewById(R.id.setup_into_account);
        into_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        profileImg=rootView.findViewById(R.id.profile_Image);
        nickname = rootView.findViewById(R.id.profile_nickname);
        introduction = rootView.findViewById(R.id.profile_introduction);
        name = rootView.findViewById(R.id.profile_name);
        id = rootView.findViewById(R.id.profile_id);
        password = rootView.findViewById(R.id.profile_pw);
        account = rootView.findViewById(R.id.profile_account);

        String url = local_url + "/yejin/mypage_main_list?member_id=" + userid;

        ProfileNetworkTask profileNetworkTask = new ProfileNetworkTask(url, null);
        profileNetworkTask.execute();

        return rootView;
    }

    // 내 메모 추가
    public class ProfileNetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public ProfileNetworkTask(String url, ContentValues values) {

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

                    JSONArray MemberList = jsonObject.getJSONArray("List");

                    for (int i = 0; i < MemberList.length(); i++) {

                        JSONObject ListObject = MemberList.getJSONObject(i);

                        String.valueOf(ListObject.getInt("member_no"));
                        ListObject.getString("member_id");
                        ListObject.getString("member_pw");
                        ListObject.getString("member_name");
                        ListObject.getString("member_nickname");
                        ListObject.getString("member_account");
                        ListObject.getString("member_introduction");
                        ListObject.getString("member_blacklist");
                        ListObject.getString("member_black_date");
                        ListObject.getString("member_black_reason");
                        ListObject.getString("member_withdraw");
                        ListObject.getString("member_joindate");
                        ListObject.getString("member_last_access");
                        ListObject.getString("member_exp");
                        ListObject.getString("member_grade");
                        ListObject.getString("member_profile_image");

                        nickname.setText( ListObject.getString("member_nickname"));
                        introduction.setText(ListObject.getString("member_nickname"));
                        name.setText(ListObject.getString("member_name"));

                        id.setText( ListObject.getString("member_id"));
                        setup_map.put("id", ListObject.getString("member_id") );

                        password.setText(ListObject.getString("member_pw"));
                        setup_map.put("pw", ListObject.getString("member_pw") );

                        if(ListObject.getString("member_account").equals("null")){
                            account.setText( "계좌를 등록해 주세요");
                        }else {
                            account.setText(ListObject.getString("member_account"));
                        }
                        String imageurl = local_url + "/";
                        imageurl = imageurl + ListObject.getString("member_profile_image") + ".jpg";

                        FileUploadNetworkTask fileUploadNetworkTask = new FileUploadNetworkTask(imageurl, null);
                        fileUploadNetworkTask.execute();

                        if (getActivity().isFinishing()) {
                            return;
                        }
                    }
                }
            } catch (JSONException e) {
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

            return result;
        }

        @Override
        protected void onPostExecute(Bitmap json) {
            super.onPostExecute(json);
            profileImg.setImageBitmap(json);

            if (getActivity().isFinishing()) {
                return;
            }
        }
    }

    //BackStack 으로 뒤로가기 버튼 누르면 전 화면으로 이동하기 위함
    @Override
    public void onBackKey() {
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnKeyBackPressedListener(null);
        activity.onBackPressed();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity)context).setOnKeyBackPressedListener(this);
    }
}
