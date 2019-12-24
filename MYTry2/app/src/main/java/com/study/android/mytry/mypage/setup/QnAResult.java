package com.study.android.mytry.mypage.setup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.study.android.mytry.R;

import java.util.HashMap;

public class QnAResult extends AppCompatActivity {

    TextView question_date;
    TextView question_content;
    TextView manager_id;
    TextView reply_date;
    TextView reply_content;

    HashMap<String, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_setup_qna_content);

        Intent intent = getIntent();
        map = (HashMap<String, String>) intent.getSerializableExtra("hashmap");

        question_date = findViewById(R.id.qna_question_date);
        question_content = findViewById(R.id.qna_question_content);
        manager_id = findViewById(R.id.qna_content_managerid);
        reply_date = findViewById(R.id.qna_reply_date);
        reply_content = findViewById(R.id.qna_reply_content);

        question_date.setText(map.get("question_date"));
        question_content.setText(map.get("question_Content"));

        if(map.get("manager_id").equals("null")){
            manager_id.setText(map.get(""));
        }else{
            manager_id.setText(map.get("manager_id"));
        }

        if(map.get("answer_date").equals("null")){
            reply_date.setText(map.get(""));
        }else{
            reply_date.setText(map.get("answer_date"));
        }

        if(map.get("question_answer").equals("null")){
            reply_content.setText(map.get(""));
        }else{
            reply_content.setText(map.get("question_answer"));
        }
    }
}
