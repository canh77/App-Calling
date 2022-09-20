package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class SumTimeCallActivity extends AppCompatActivity {
    ImageView imgbackrecently;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_time_call);
        imgbackrecently = findViewById(R.id.imgbackrecently);
        imgbackrecently.setOnClickListener(view -> {
            finish();
        });
    }
}