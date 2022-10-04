package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsMsgActivity extends AppCompatActivity {
    ImageView imgbackmsg;
    TextView tvnamedetail,tvphonedetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_msg);
        imgbackmsg = findViewById(R.id.imgbackmsg);
        tvnamedetail = findViewById(R.id.tvnamedetail);
        tvphonedetail = findViewById(R.id.tvphonedetail);
        //nhận dữ liệu
        String name = getIntent().getStringExtra("user");
        tvnamedetail.setText(name);


        imgbackmsg.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}