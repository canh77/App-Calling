package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SelectedNationalPhoneActivity extends AppCompatActivity {
    ImageView imgbackggmeet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_national_phone);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
        imgbackggmeet = findViewById(R.id.imgbackggmeet);

    }

    public void backggmeet(View view) {
        Intent intent = new Intent(SelectedNationalPhoneActivity.this,GoogleMeetActivity.class);
        startActivity(intent);
    }
}