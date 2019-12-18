package com.study.android.mytry.mypage.setup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.android.mytry.R;

public class ProfileModify extends Fragment {

    LinearLayout into_profile;
    LinearLayout into_introduction;
    LinearLayout into_pw;
    LinearLayout into_account;

    TextView nickname;
    TextView introduction;
    TextView name;
    TextView id;
    TextView password;
    TextView account;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.mypage_setup_profile, container, false);

        into_profile = rootView.findViewById(R.id.setup_into_profile);
        into_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PasswordModify.class);
                startActivity(intent);
            }
        });

        into_introduction = rootView.findViewById(R.id.setup_into_introduction);
        into_introduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        into_pw= rootView.findViewById(R.id.setup_into_pw);
        into_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        into_account= rootView.findViewById(R.id.setup_into_account);
        into_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        nickname= rootView.findViewById(R.id.profile_nickname);
        introduction= rootView.findViewById(R.id.profile_introduction);
        name= rootView.findViewById(R.id.profile_name);
        id= rootView.findViewById(R.id.profile_id);
        password= rootView.findViewById(R.id.profile_pw);
        account= rootView.findViewById(R.id.profile_account);

        return rootView;
    }
}
