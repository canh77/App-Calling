package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logindemo.adapter.PhoneBookAdapter;
import com.example.logindemo.model.PhoneBook;

import java.util.ArrayList;

public class DetailsPhoneBookActivity extends AppCompatActivity {
    ImageView imgbackphonebook, imgcallpb, imgmessagepb, imgotherpb, imgsharepb;
    Button btndiarypb;
    TextView tvNamePhoneNumber, tvNumberPhone;
    TextView tvNameCache,tvPhoneCache;


    //Xóa phần tử
    private PhoneBookAdapter phoneBookAdapter;
    private ArrayList<PhoneBook> bookArrayList;
    private ArrayList<PhoneBook> mBooks;

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

        imgsharepb = findViewById(R.id.imgsharepb);
        imgsharepb.setOnClickListener(view -> {
            menuShare(view);
        });

        //nhận dữ liệu
        String name = getIntent().getStringExtra("user");
        tvNamePhoneNumber.setText(name);
        String numberPhone = getIntent().getStringExtra("phone");
        tvNumberPhone.setText(numberPhone);

        imgcallpb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsPhoneBookActivity.this, CallPhoneBookActivity.class);
                //nhận
                String username = tvNamePhoneNumber.getText().toString().trim();
                String phonenum = tvNumberPhone.getText().toString();
                intent.putExtra("username", username);
                intent.putExtra("PhoneNumber", phonenum);
                startActivity(intent);
            }
        });

        imgmessagepb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsPhoneBookActivity.this, MessengerActivity.class);
                String username = tvNamePhoneNumber.getText().toString().trim();
                intent.putExtra("username", username);
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
        //Nhận dữ liệu
        String username = tvNamePhoneNumber.getText().toString().trim();
        String phonenum = tvNumberPhone.getText().toString();
        intent.putExtra("username", username);
        intent.putExtra("PhoneNumber", phonenum);
        startActivity(intent);
    }

    public void menuShare(View view) {
        PopupMenu menu = new PopupMenu(this, imgsharepb);
        menu.inflate(R.menu.menu_sharepb);

        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.mn_filecard:
                        Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                        Uri data = Uri.parse("mailto:? subject=" + "Android Developer" + "&body=" + "Hey!" + "&to=" + "abc123@gmail.com");
                        mailIntent.setData(data);
                        startActivity(Intent.createChooser(mailIntent, "Send Mail"));
                        break;
                    case R.id.mn_doucument:
                        Intent mailIntent1 = new Intent(Intent.ACTION_VIEW);
                        Uri data1 = Uri.parse("mailto:? subject=" + "Android Developer" + "&body=" + "Hey!" + "&to=" + "abc123@gmail.com");
                        mailIntent1.setData(data1);
                        startActivity(Intent.createChooser(mailIntent1, "Send Mail."));
                        break;
                }
                return false;
            }
        });
        menu.show();
    }

    public void menudetailsphone(View view) {
        PopupMenu menu = new PopupMenu(this, imgotherpb);
        menu.inflate(R.menu.menu_settingdetailspb);
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.mn_deletedetailpb:
                        openDialogDeleteGarbage();
                        break;
                    case R.id.mn_qr:
                        Intent intent = new Intent(DetailsPhoneBookActivity.this, QrPhoneBookActivity.class);
                        startActivity(intent);
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
        Intent intent = new Intent(DetailsPhoneBookActivity.this, DiaryPhoneBookActivity.class);
        startActivity(intent);

    }

    public void goPositionCache(View view) {
        Intent intent = new Intent(DetailsPhoneBookActivity.this, PossitionCacheActivity.class);
        //truyền dữ liệu qua vị trí bộ nhớ
        String username = tvNamePhoneNumber.getText().toString().trim();
        String phonenum = tvNumberPhone.getText().toString();
        intent.putExtra("username", username);
        intent.putExtra("PhoneNumber", phonenum);
        startActivity(intent);
    }

    //dialog delete numberphone
    public void openDialogDeleteGarbage() {
        Button btnExit, btnDeletePhone;
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_delete_phonenumber);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        //chạm bên ngoài sẽ đóng dialog
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btnExit = dialog.findViewById(R.id.btnExit);
        btnExit.setOnClickListener(view -> {
            //đóng dialog
            dialog.dismiss();
        });

        //khi click vào xóa
        btnDeletePhone = dialog.findViewById(R.id.btnDeleteGarbage);
        btnDeletePhone.setOnClickListener(view -> {
            onBackPressed();
        });
        dialog.show();
    }


}