package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsPhoneBookActivity extends AppCompatActivity {
    ImageView imgbackphonebook, imgcallpb, imgmessagepb, imgotherpb;
    Button btndiarypb;
    TextView tvNamePhoneNumber,tvNumberPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_phone_book);
        imgbackphonebook = findViewById(R.id.imgbackphonebook);
        imgcallpb = findViewById(R.id.imgcallpb);
        imgmessagepb = findViewById(R.id.imgmessagepb);
        imgotherpb = findViewById(R.id.imgotherpb);
        btndiarypb = findViewById(R.id.btndiarypb);
        tvNamePhoneNumber = findViewById(R.id.tvNamePhoneNumber);
        tvNumberPhone = findViewById(R.id.tvNumberPhone);

        //nhận dữ liệu
        String name = getIntent().getStringExtra("user");
        tvNamePhoneNumber.setText(name);
        String numberPhone = getIntent().getStringExtra("phone");
        tvNumberPhone.setText(numberPhone);

        imgcallpb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsPhoneBookActivity.this, CallPhoneBookActivity.class);
                String username = tvNamePhoneNumber.getText().toString().trim();
                String phonenum = tvNumberPhone.getText().toString();
                intent.putExtra("username",username);
                intent.putExtra("PhoneNumber",phonenum);
                startActivity(intent);
            }
        });

        imgmessagepb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsPhoneBookActivity.this, MessengerActivity.class);
                startActivity(intent);
            }
        });
    }

    public void backphonebook(View view) {
        //dùng onBackPressed sẽ quay lại màn hình main và không mất đi dữ liệu
        onBackPressed();
    }


    public void goCall(View view) {
        Intent intent = new Intent(DetailsPhoneBookActivity.this, CallPhoneBookActivity.class);
        startActivity(intent);
    }

    public void goMessenger(View view) {
        Intent intent = new Intent(DetailsPhoneBookActivity.this, MessengerActivity.class);
        startActivity(intent);
    }

    public void goGoogleMeet(View view) {
        Intent intent = new Intent(DetailsPhoneBookActivity.this, GoogleMeetActivity.class);
        startActivity(intent);
    }

    public void goEditNumberPhone(View view) {
        Intent intent = new Intent(DetailsPhoneBookActivity.this, AddNumberPhoneActivity.class);
        startActivity(intent);
    }

    public void menudetailsphone(View view) {
        PopupMenu menu = new PopupMenu(this, imgotherpb);
        menu.inflate(R.menu.menu_settingdetailspb);
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.mn_deletedetailpb:
                        Toast.makeText(view.getContext(), "xóa danh bạ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mn_qr:
                        Toast.makeText(view.getContext(), "mã QR", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mn_blockpb:
                        Toast.makeText(view.getContext(), "chặn danh bạ", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        menu.show();
    }

    public void goDiary(View view) {
        Intent intent = new Intent(DetailsPhoneBookActivity.this,DiaryPhoneBookActivity.class);
        startActivity(intent);
    }

    public void goPositionCache(View view) {
        Intent intent = new Intent(DetailsPhoneBookActivity.this,PossitionCacheActivity.class);
        startActivity(intent);
    }


}