package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logindemo.fragment.PhoneBookragment;

public class SpeedDialActivity extends AppCompatActivity {
    ImageView imglistPhoneBook, imgbackphoneb, imgsimmsg;
    LinearLayout lnvoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_dial);
        imglistPhoneBook = findViewById(R.id.imglistPhoneBook);
        imgbackphoneb = findViewById(R.id.imgbackphoneb);
        imgsimmsg = findViewById(R.id.imgsimmsg);
        lnvoice = findViewById(R.id.lnvoice);

        lnvoice.setOnClickListener(view -> {
            openDialogVoice(this);
        });

        imgsimmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getApplication().getApplicationContext(), imgsimmsg);
                popupMenu.getMenuInflater().inflate(R.menu.menu_selectpb, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
//                            case R.id.mn_zero:
//                                break;
                            case R.id.mn_one:
                                break;
                            case R.id.mn_two:
                                break;
                            case R.id.mn_three:
                                break;
                            case R.id.mn_four:
                                break;
                            case R.id.mn_five:
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        imgbackphoneb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imglistPhoneBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpeedDialActivity.this, SelectedPhoneBookActivity.class);
                startActivity(intent);

            }
        });
    }

    //dialog selected numberphone
    protected void openDialogVoice(final Context context) {
        Button btnExit, btnAddNumber;
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_voicemail);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        {
            btnAddNumber = dialog.findViewById(R.id.btnAddNumber);
            btnAddNumber.setOnClickListener(view -> {
                Intent intent = new Intent(getApplicationContext(), VoiceMailActivity.class);
                startActivity(intent);
                finish();
            });
            btnExit = dialog.findViewById(R.id.btnExit);
            btnExit.setOnClickListener(view -> {
                dialog.dismiss();
            });

        }

        {

            dialog.show();
        }

    }}