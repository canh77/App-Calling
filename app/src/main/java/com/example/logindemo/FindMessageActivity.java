package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class FindMessageActivity extends AppCompatActivity {
    EditText edFindMSG;
    ImageView imgdeletemsg,imgbackphoneb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_message);
        edFindMSG = findViewById(R.id.edFindMSG);
        imgdeletemsg = findViewById(R.id.imgdeletemsg);
        imgbackphoneb = findViewById(R.id.imgbackphoneb);

        imgdeletemsg.setOnClickListener(view -> {
            edFindMSG.setText("");
        });
        imgbackphoneb.setOnClickListener(view -> {
            onBackPressed();
        });

        edFindMSG.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0){
                    imgdeletemsg.setVisibility(View.GONE);
                }else {
                    imgdeletemsg.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}