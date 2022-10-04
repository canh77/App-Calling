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
    LinearLayout lnContent1, lnContent2, lnContent3, lnContent5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_gg_meet);

        imghelpggmeet = findViewById(R.id.imghelpggmeet);
        lnContent1 = findViewById(R.id.lnContent1);
        lnContent2 = findViewById(R.id.lnContent2);
        lnContent5 = findViewById(R.id.lnContent5);
        lnContent3 = findViewById(R.id.lnContent3);

        lnContent1.setOnClickListener(view -> {
            lnContent2.setVisibility(View.VISIBLE);
        });
        lnContent2.setOnClickListener(view -> {
            lnContent2.setVisibility(View.GONE);
        });

        lnContent5.setOnClickListener(view -> {
            lnContent3.setVisibility(View.VISIBLE);
        });
        lnContent3.setOnClickListener(view -> {
            lnContent3.setVisibility(View.GONE);
        });
    }

    public void cancelggmeet(View view) {
        Intent intent = new Intent(HelpGgMeetActivity.this, GoogleMeetActivity.class);
        startActivity(intent);
        finish();
    }


    public void menu(View view) {
        PopupMenu menu = new PopupMenu(this, imghelpggmeet);
        menu.inflate(R.menu.menu_view);
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.mn_postshare:
                        break;
                    case R.id.mn_feedback:
                        break;
                    case R.id.mn_viewggplay:
                        break;
                }
                return false;
            }
        });
        menu.show();
    }
}