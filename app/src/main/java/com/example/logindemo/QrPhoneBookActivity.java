package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class QrPhoneBookActivity extends AppCompatActivity {
    ImageView imgbackpbs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_phone_book);
        imgbackpbs = findViewById(R.id.imgbackpbs);
        imgbackpbs.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}