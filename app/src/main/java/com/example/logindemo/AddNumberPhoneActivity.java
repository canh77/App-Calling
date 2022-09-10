package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.logindemo.R;

public class AddNumberPhoneActivity extends AppCompatActivity {
    EditText edName, edPhone;
    Button btnCancel, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_number_phone);
        edName = findViewById(R.id.edName);
        edPhone = findViewById(R.id.edPhone);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);

    }

    public void cancel(View view) {
        Intent intent = new Intent(AddNumberPhoneActivity.this,MainActivity.class);
        startActivity(intent);
    }
}