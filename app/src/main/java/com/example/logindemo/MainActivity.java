package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.logindemo.adapter.ViewPageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private ViewPageAdapter mAdapter;
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager2;
    //Image
    ImageView img_add,imgphone;


    //khai báo các tab bên trong string
    private int[] mTabTitles = new int[]{R.string.tab_danhba_title,R.string.tab_ganday_title,R.string.tab_banphim_title};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_add = findViewById(R.id.img_add);


        mAdapter = new ViewPageAdapter(this);
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager2 = findViewById(R.id.viewPager);
        mViewPager2.setAdapter(mAdapter);

        new TabLayoutMediator(mTabLayout, mViewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
            tab.setText(MainActivity.this.getResources().getString(mTabTitles[position]));
            }
        }).attach();

    }
    public  void addPhone(View view){
        Intent intent = new Intent(MainActivity.this, AddNumberPhoneActivity.class);
        startActivity(intent);
    }



}