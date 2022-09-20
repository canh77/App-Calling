package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class PossitionCacheActivity extends AppCompatActivity {
    ImageView imgbackdetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_possition_cache);
        imgbackdetails = findViewById(R.id.imgbackdetails);
        imgbackdetails.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),DetailsPhoneBookActivity.class);
            startActivity(intent);
            finish();
        });
    }
}