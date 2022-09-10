package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GoogleMeetActivity extends AppCompatActivity {
    ImageView imgSelectedNational;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_meet);

        imgSelectedNational = findViewById(R.id.imgSelectedNational);


    }

    public void select(View view) {
        Intent intent = new Intent(GoogleMeetActivity.this,SelectedNationalPhoneActivity.class);
        startActivity(intent);
    }

    public void goHelp(View view) {
        Intent intent = new Intent(GoogleMeetActivity.this,HelpGgMeetActivity.class);
        startActivity(intent);
    }
}