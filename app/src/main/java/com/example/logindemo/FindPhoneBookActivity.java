package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.logindemo.adapter.PhoneBookAdapter;
import com.example.logindemo.fragment.PhoneBookragment;

public class FindPhoneBookActivity extends AppCompatActivity {
    EditText edFindpb;
    ImageView imgmicpb,imgdeletepb,imgbackpb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_phone_book);


        imgmicpb = findViewById(R.id.imgmicpb);
        imgdeletepb = findViewById(R.id.imgdeletepb);
        imgbackpb = findViewById(R.id.imgbackpb);
        edFindpb = findViewById(R.id.edFindpb);

        //Xóa text tìm kiếm
        imgdeletepb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edFindpb.setText("");
            }
        });

        //back về activity trước như hệ thống dùng onBackPr
        imgbackpb.setOnClickListener(view -> {
//            onBackPressed();
            finish();
        });

        edFindpb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0){
                    imgmicpb.setVisibility(View.VISIBLE);
                    imgdeletepb.setVisibility(View.GONE);
                }else{
                    imgmicpb.setVisibility(View.GONE);
                    imgdeletepb.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}