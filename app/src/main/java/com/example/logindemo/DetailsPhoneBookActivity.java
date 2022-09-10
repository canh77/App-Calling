package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DetailsPhoneBookActivity extends AppCompatActivity {
    ImageView imgbackphonebook,imgcallpb,imgmessagepb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_phone_book);
        imgbackphonebook = findViewById(R.id.imgbackphonebook);
        imgcallpb = findViewById(R.id.imgcallpb);
        imgmessagepb = findViewById(R.id.imgmessagepb);

        imgcallpb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsPhoneBookActivity.this,CallPhoneBookActivity.class);
                startActivity(intent);
            }
        });

        imgmessagepb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsPhoneBookActivity.this,MessengerActivity.class);
                startActivity(intent);
            }
        });
    }

    public void backphonebook(View view) {
        Intent intent = new Intent(DetailsPhoneBookActivity.this,MainActivity.class);
        startActivity(intent);
    }
}