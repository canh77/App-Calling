package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DiaryPhoneBookActivity extends AppCompatActivity {
    ImageView imgbackphoneb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_phone_book);
        imgbackphoneb = findViewById(R.id.imgbackphoneb);
    }

    public void backdetails(View view) {
        Intent intent = new Intent(DiaryPhoneBookActivity.this,DetailsPhoneBookActivity.class);
        startActivity(intent);
    }
}