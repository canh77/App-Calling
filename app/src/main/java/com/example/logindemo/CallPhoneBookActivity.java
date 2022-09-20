package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logindemo.utils.Constants;

public class CallPhoneBookActivity extends AppCompatActivity {
    ImageView img_call;
    TextView tvNameUser, tvNumberphones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_phone_book);
        img_call = findViewById(R.id.img_call);
        tvNameUser = findViewById(R.id.tvNameUser);
        tvNumberphones = findViewById(R.id.tvNumberphones);


        //làm mờ thanh trạng thái
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        //nhận dữ liệu
        String phone = getIntent().getStringExtra("PhoneNumber");
        tvNumberphones.setText(phone);

        String username = getIntent().getStringExtra("username");
        tvNameUser.setText(username);


//        String phonenum = getIntent().getStringExtra("phonenum");
//        tvNumberphones.setText(phonenum);
//        Boolean isPhoneNumber = Patterns.PHONE.matcher(username).matches();
//        Boolean isPhoneNumber = Patterns.PHONE.matcher(phonenum).matches();

        boolean isPhoneNumber = Patterns.PHONE.matcher(phone).matches();
        Log.d("///","isPhoneNumber"+isPhoneNumber);
        Log.d("///","phone"+phone);
        if (!isPhoneNumber){
            Toast.makeText(this, "số điện thoại ko hợp lệ", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "số điện thoại hợp lệ", Toast.LENGTH_SHORT).show();
        }

        if (!isPhoneNumber) {
            MediaPlayer mp;
            mp=MediaPlayer.create(getApplicationContext(),R.raw.voice);
            mp.start();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            },4000);

        } else {
            MediaPlayer mp;
            mp=MediaPlayer.create(getApplicationContext(),R.raw.fail);
            mp.start();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            },4000);
        }
//        tvNameUser.setText("Me");
//        tvNumberphones.setText("0235488875");
    }

    public void cancelCall(View view) {
        Intent intent = new Intent(CallPhoneBookActivity.this, MainActivity.class);
        onBackPressed();
    }
}