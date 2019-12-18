package com.study.android.mytry.mypage.setup;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.study.android.mytry.R;

public class PasswordModify extends AppCompatActivity {

    TextInputLayout input_edit;
    Button modify_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_password_modify);

        input_edit = findViewById(R.id.editText_input);
        input_edit.setCounterMaxLength(15);

        modify_send = findViewById(R.id.nickname_modify_btn);
        modify_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 통신
            }
        });

    }
}
