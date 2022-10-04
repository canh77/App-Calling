package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.logindemo.adapter.NumberAdapter;
import com.example.logindemo.adapter.NumbersAdapter;
import com.example.logindemo.model.Number;

import java.util.ArrayList;

public class BoardCallActivity extends AppCompatActivity {

    NumbersAdapter numbersAdapter;
    private EditText edNumberInput;
    String phoneNumbers = "";
    private RecyclerView rvKeyboard1;
    ImageView imgCallkb, img_otherkeyboard, img_search, img_googlekeyboard, img_deletekeyboard;
    Intent intent;
    private static final int REQUEST_CALL = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_call);

        //mapping trong Fragment là phải thêm view trước findViewByID
        rvKeyboard1 = findViewById(R.id.rvKeyboard1);
        img_search = findViewById(R.id.img_search);
        edNumberInput = findViewById(R.id.edNumberInput);
        imgCallkb = findViewById(R.id.img_callkb);

        edNumberInput.setText("0399075408");

        imgCallkb.setOnClickListener(view1 -> {
                makePhoneCall1();
        });

        img_deletekeyboard = findViewById(R.id.img_deletekeyboard);
        img_googlekeyboard = findViewById(R.id.img_googlekeyboard);

        img_googlekeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), GoogleMeetActivity.class);
                startActivity(intent);
            }
        });

//        nhấn giữ nút xóa
        img_deletekeyboard.setOnLongClickListener(view1 -> {
            phoneNumbers = "";
            edNumberInput.setText(phoneNumbers);
            img_deletekeyboard.setVisibility(View.GONE);
            img_googlekeyboard.setVisibility(View.GONE);
            return false;
        });

        //xóa từng item
        img_deletekeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phoneNumbers != null && phoneNumbers.length() > 0) {
                    phoneNumbers = phoneNumbers.substring(0, phoneNumbers.length() - 1);
                    edNumberInput.setText(phoneNumbers);
                    if (phoneNumbers.equals("")) {
                        img_deletekeyboard.setVisibility(View.GONE);
                        img_googlekeyboard.setVisibility(View.GONE);
                    } else {
                        img_deletekeyboard.setVisibility(View.VISIBLE);
                        img_googlekeyboard.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        //Xử lí ko cho hiện bàn phím
        edNumberInput.setInputType(InputType.TYPE_NULL);

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindPhoneBookActivity.class);
                startActivity(intent);
            }
        });

        img_otherkeyboard = findViewById(R.id.img_otherkeyboard);
        img_otherkeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), img_otherkeyboard);
                popupMenu.getMenuInflater().inflate(R.menu.menu_keyboard, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.mn_numberspeedkb:
                                intent = new Intent(view.getContext(), SpeedDialActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.mn_openkb:
                                Toast.makeText(getApplicationContext(), "Điện thoại sẽ luôn mở với chế độ xem bàn phím", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.mn_settingkb:
                                intent = new Intent(view.getContext(), SettingPhoneActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.mn_contactuskb:
                                intent = new Intent(view.getContext(), ContactUsActivity.class);
                                startActivity(intent);
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        ArrayList<Number> numbers = new ArrayList<>();
        numbers.add(new Number("1", ".."));
        numbers.add(new Number("2", "ABC"));
        numbers.add(new Number("3", "DEF"));
        numbers.add(new Number("4", "GHI"));
        numbers.add(new Number("5", "JKL"));
        numbers.add(new Number("6", "MNO"));
        numbers.add(new Number("7", "PQRS"));
        numbers.add(new Number("8", "TUV"));
        numbers.add(new Number("9", "WXYZ"));
        numbers.add(new Number("*", ""));
        numbers.add(new Number("0", "+"));
        numbers.add(new Number("#", ""));
        numbersAdapter = new NumbersAdapter(numbers,getApplicationContext());
        rvKeyboard1.setLayoutManager(new GridLayoutManager(this, 3));
        rvKeyboard1.setAdapter(numbersAdapter);

    }
    //Gọi điện thoại
    private void makePhoneCall1() {
        String number = edNumberInput.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                //trả về chuỗi rỗng sau khi nhập số điện thoại
                phoneNumbers = "";
                edNumberInput.getText().clear();
            }
        } else {
            Toast.makeText(this, "Hãy nhập số điện thoại", Toast.LENGTH_SHORT).show();
        }
    }

    //override cho phương thức cấp quyền
    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL){
            if (grantResults.length>0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED);
            makePhoneCall1();
        }else {
            Toast.makeText(this, "Permisson DENIED", Toast.LENGTH_SHORT).show();
        }
    }

}
