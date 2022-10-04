package com.example.logindemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.logindemo.R;

public class AddNumberPhoneActivity extends AppCompatActivity {
    EditText edNamepb, edPhonepb;
    Button btnCancel, btnSave;

    // Biến constant được dùng để định danh dữ liệu được truyền giữa các Activity
    public static final String EXTRA_DATA = "EXTRA_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_number_phone);
        edNamepb = findViewById(R.id.edNamepb);
        edPhonepb = findViewById(R.id.edPhonepb);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);

        //Nhận dữ liệu từ details
        String phone = getIntent().getStringExtra("username");
        edNamepb.setText(phone);
        String username = getIntent().getStringExtra("PhoneNumber");
        edPhonepb.setText(username);

        btnSave.setOnClickListener(view -> {
            String userName = edNamepb.getText().toString().trim();
            String phoneNumber = edPhonepb.getText().toString();
            if (userName.equals("") || phoneNumber.equals("")) {
                Toast.makeText(this, "Bạn phải nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent();
                intent.putExtra("name", userName);
                intent.putExtra("phone", phoneNumber);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void cancel(View view) {
        Intent intent = new Intent(AddNumberPhoneActivity.this, MainActivity.class);
        onBackPressed();
        finish();
    }

}