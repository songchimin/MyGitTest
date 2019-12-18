package com.study.android.mytry.mypage.setup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.study.android.mytry.R;
import com.study.android.mytry.login.Login;


import static kr.co.bootpay.Bootpay.finish;

public class SetupMain extends Fragment {

    LinearLayout profile;
    LinearLayout complaint;
    Button logout;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.mypage_setup_main, container, false);

        profile = rootView.findViewById(R.id.setup_into_profile);
        profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.pull_in_right, R.anim.push_out_left, R.anim.pull_in_left, R.anim.push_out_right);

                fragmentTransaction.replace(R.id.mypage, new ProfileModify()).addToBackStack(null).commit();
            }
        });

        complaint= rootView.findViewById(R.id.setup_into_complaint);
        complaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        logout = rootView.findViewById(R.id.setup_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(getActivity(), Login.class));
                finish();
            }
        });

        return rootView;
    }
}
