package com.study.android.mytry.chat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.study.android.mytry.R;

import java.text.SimpleDateFormat;

public class private_chat extends AppCompatActivity {
    private static final String TAG = "lecture";

    SimpleDateFormat format1 = new SimpleDateFormat ( "hh:mm:ss");
    String format_time1;

    //firebase auth object
    private FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String user_email;

    FirebaseFirestore db;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    private String CHAT_NAME;
    private String USER_NAME;

    private ListView chat_view;
    private EditText chat_edit;
    private Button chat_send;

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_private);

        chat_view = findViewById(R.id.chat_view);
        chat_edit = findViewById(R.id.chat_edit);
        chat_send = findViewById(R.id.chat_sent);

        //채팅방이름 = 챌린지 번호.
        CHAT_NAME = "1";

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        user_email = user.getEmail();

//        db = FirebaseFirestore.getInstance();
//
//        db.collection("MyWeather")
//                .document(user_email.toString())
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if (task.isSuccessful()) {
//                            System.out.println(user_email);
//                            DocumentSnapshot documentSnapshot = task.getResult();
//
//                            USER_NAME = (String) documentSnapshot.get("user_name");
//
//                        } else {
//                            Log.d("yejin", "Cached document failed: " + task.getException());
//                            System.out.println("안됨");
//                        }
//                    }
//                });
        openChat(CHAT_NAME);

        // 메시지 전송 버튼에 대한 클릭 리스너 지정
        chat_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chat_edit.getText().toString().equals(""))
                    return;

                format_time1 = format1.format (System.currentTimeMillis());
                ChatDTO chat = new ChatDTO(USER_NAME, chat_edit.getText().toString(), format_time1); //ChatDTO를 이용하여 데이터를 묶는다.
                databaseReference.child("chat").child(CHAT_NAME).push().setValue(chat); // 데이터 푸쉬

                chat_edit.setText(""); //입력창 초기화

            }
        });

    }


    private void addMessage(DataSnapshot dataSnapshot, ArrayAdapter<String> adapter) {
        ChatDTO chatDTO = dataSnapshot.getValue(ChatDTO.class);

        adapter.add(chatDTO.getUserName() + " 님\n\n" + chatDTO.getMessage() + "\n                                            " +
                "                                                   " + chatDTO.getTime());
        adapter.notifyDataSetChanged();

    }

    private void removeMessage(DataSnapshot dataSnapshot, ArrayAdapter<String> adapter) {
        ChatDTO chatDTO = dataSnapshot.getValue(ChatDTO.class);
        adapter.remove(chatDTO.getUserName() + " : " + chatDTO.getMessage());
    }

    private void openChat(String chatName) {
        // 리스트 어댑터 생성 및 세팅
        final ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(activity, R.layout.chat_item_view, R.id.textView2);

        chat_view.setAdapter(adapter);
        chat_view.setSelection(adapter.getCount() - 1);


        // 데이터 받아오기 및 어댑터 데이터 추가 및 삭제 등..리스너 관리
        databaseReference.child("chat").child(chatName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                addMessage(dataSnapshot, adapter);

                Log.e("LOG", "s:"+s);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                removeMessage(dataSnapshot, adapter);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
