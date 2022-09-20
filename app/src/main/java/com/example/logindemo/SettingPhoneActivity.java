package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SettingPhoneActivity extends AppCompatActivity {
    ImageView imgbackfragmentpb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_phone);

        imgbackfragmentpb = findViewById(R.id.imgbackfragmentpb);
        imgbackfragmentpb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}