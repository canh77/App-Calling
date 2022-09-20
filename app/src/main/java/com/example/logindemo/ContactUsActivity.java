package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class ContactUsActivity extends AppCompatActivity {
    ImageView imgbackpbook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        imgbackpbook = findViewById(R.id.imgbackpbook);
        imgbackpbook.setOnClickListener(view -> {
            finish();
        });

    }
}