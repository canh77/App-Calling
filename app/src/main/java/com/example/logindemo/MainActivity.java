package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logindemo.adapter.ViewPageAdapter;
import com.example.logindemo.learn.Animal;
import com.example.logindemo.model.PhoneBook;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ViewPageAdapter mAdapter;
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager2;
    private  Test test;
    private  DisplayNumber displayNumber;
    private  boolean check;
    //Image
    ImageView img_add, img_search;

//    phoneBookArrayList.clear();
//    //nhập dữ liệu từ edit text
//    String content = edtSearch.getText().toString();
//    //tạo hàm for each để duyệt các ptu của mảng
//                for (PhoneBook phoneBook : mPhoneBooks) {
//        //so sánh ptu
//        if (phoneBook.getName().toLowerCase(Locale.ROOT).contains(content)) {
//            //thêm vào list tạm thời
//            phoneBookArrayList.add(phoneBook);
//        }
//    }
//    //cập nhật lại list
//                adapter.notifyDataSetChanged();

    //biến cố định
//    mPhoneBooks = new ArrayList<>();
//    //add tất cả các ptu
//        mPhoneBooks.addAll(phoneBookArrayList);

    //khai báo các tab bên trong string
    private int[] mTabTitles = new int[]{R.string.tab_danhba_title, R.string.tab_ganday_title, R.string.tab_banphim_title};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // test khởi tạo đối tượng
//        Animal animal = new Animal("abc","đỏ","nam",false,20);
//        animal.show();
//        animal.getTen2();
//        animal.coin+=5;

        img_add = findViewById(R.id.img_add);
        img_search = findViewById(R.id.img_search);

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

    public void addPhone(View view) {
        Intent intent = new Intent(MainActivity.this, AddNumberPhoneActivity.class);
        startActivityForResult(intent, 20);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //nhận dữ liệu
        if (requestCode == 20 && resultCode == RESULT_OK) {
            final String result = data.getStringExtra(AddNumberPhoneActivity.EXTRA_DATA);
            //lấy dữ liệu
            final String name = data.getStringExtra("name");
            final String  phone = data.getStringExtra("phone");
//            Toast.makeText(this, "Add successful: " + phone , Toast.LENGTH_SHORT).show();
            //Tạo đối tượng để lấy rồi add vào
            PhoneBook phoneBook = new PhoneBook("0",name,phone);
            displayNumber.display(phoneBook);
        } else {
            Toast.makeText(this, "Không có data trả về", Toast.LENGTH_SHORT).show();
        }
    }
//    @Override
//    public void onBackPressed() {
//      //  super.onBackPressed();
//        test.show();
//
//    }

    public interface Test{
        void show();
    }

    public void setTest(Test test){
        this.test = test;
    }

    public interface DisplayNumber{
        void display(PhoneBook phoneBook);
    }
    public  void  setDisplayNumber(DisplayNumber displayNumber){
        this.displayNumber = displayNumber;
    }

}