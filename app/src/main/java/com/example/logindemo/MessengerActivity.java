package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MessengerActivity extends AppCompatActivity {
    ImageView  imgbackmsg,imgsendmsg,imgiconmsg,imgsimmsg,imgiconmsgone,imgmicromsg;
    EditText edTextInput;
    LinearLayout lnTextInput,lnstatus,lnnewstatus,lnnewTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        //làm mờ thanh trạng thái
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);

        imgbackmsg = findViewById(R.id.imgbackmsg);
        imgiconmsg = findViewById(R.id.imgiconsmsg);
        imgsendmsg = findViewById(R.id.imgsendmsg);
        imgiconmsgone = findViewById(R.id.imgiconmsgone);
        imgmicromsg = findViewById(R.id.imgmicromsg);
        imgsimmsg = findViewById(R.id.imgsimmsg);
        edTextInput = findViewById(R.id.edTextInput);


        edTextInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0){
                    imgiconmsg.setVisibility(View.GONE);
                    imgsendmsg.setVisibility(View.GONE);
                    imgiconmsgone.setVisibility(View.VISIBLE);
                    imgsimmsg.setVisibility(View.VISIBLE);
                    imgmicromsg.setVisibility(View.VISIBLE);
                }else {
                    imgiconmsg.setVisibility(View.VISIBLE);
                    imgsendmsg.setVisibility(View.VISIBLE);
                    imgiconmsgone.setVisibility(View.GONE);
                    imgsimmsg.setVisibility(View.GONE);
                    imgmicromsg.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void cancelPhoneBook(View view) {
        Intent intent = new Intent(MessengerActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void goGgmeet(View view) {
        Intent intent = new Intent(MessengerActivity.this,GoogleMeetActivity.class);
        startActivity(intent);
    }
}