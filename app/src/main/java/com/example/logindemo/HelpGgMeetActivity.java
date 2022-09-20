package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

public class HelpGgMeetActivity extends AppCompatActivity {

    ImageView imghelpggmeet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_gg_meet);

        imghelpggmeet = findViewById(R.id.imghelpggmeet);

    }

    public void cancelggmeet(View view) {
        Intent intent = new Intent(HelpGgMeetActivity.this, GoogleMeetActivity.class);
        startActivity(intent);
    }


    public void menu(View view) {
        PopupMenu menu = new PopupMenu(this, imghelpggmeet);
        menu.inflate(R.menu.menu_view);
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.mn_postshare:
                        Toast.makeText(view.getContext(), "Đây là chia sẻ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mn_feedback:
                        Toast.makeText(view.getContext(), "Đây là phản hồi", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mn_viewggplay:
                        Toast.makeText(view.getContext(), "Xem cửa hàng CHPlay", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        menu.show();
    }
}