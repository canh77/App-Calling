package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PossitionCacheActivity extends AppCompatActivity {
    ImageView imgbackdetails;
    EditText edNamepb, edPhonepb;
    TextView tvNameCache, tvPhoneCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_possition_cache);
        imgbackdetails = findViewById(R.id.imgbackdetails);
        tvNameCache = findViewById(R.id.tvNameCache);
        tvPhoneCache = findViewById(R.id.tvPhoneCache);

        //nhận dữ liệu bên chi tiết truyền qua
        String phone = getIntent().getStringExtra("username");
        tvNameCache.setText(phone);
        String username = getIntent().getStringExtra("PhoneNumber");
        tvPhoneCache.setText(username);

        imgbackdetails.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}