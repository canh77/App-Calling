package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.logindemo.adapter.PhoneBookAdapter;
import com.example.logindemo.fragment.PhoneBookragment;
import com.example.logindemo.model.PhoneBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FindPhoneBookActivity extends AppCompatActivity {
    ImageView imgmicpb, imgdeletepb, imgbackpb;
    RecyclerView rvListfindpb;
    ListView lvList;
    EditText edtSearch;

    //Tìm kiếm dữ liệu
    private PhoneBookAdapter adapter;
    //tạm thời toàn cục
    private ArrayList<PhoneBook> phoneBookArrayList;
    // tạo ra 1 biến cố định toàn cục
    private ArrayList<PhoneBook> mPhoneBooks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_phone_book);

        imgmicpb = findViewById(R.id.imgmicpb);
        imgdeletepb = findViewById(R.id.imgdeletepb);
        imgbackpb = findViewById(R.id.imgbackpb);
        rvListfindpb = findViewById(R.id.rvListfindpb);
        buildRecyclerView();
        edtSearch = findViewById(R.id.edtSearch);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //ẩn hiện
                if (charSequence.length() == 0) {
                    imgmicpb.setVisibility(View.VISIBLE);
                    imgdeletepb.setVisibility(View.GONE);
                } else {
                    imgmicpb.setVisibility(View.GONE);
                    imgdeletepb.setVisibility(View.VISIBLE);
                }

                //làm sạch list tạm thời
                phoneBookArrayList.clear();
                //Tạo biến content để nhập vào edtSearch
                String content = edtSearch.getText().toString().trim();

                //tạo vòng lặp for each để kiểm tra
                for (PhoneBook phoneBook : mPhoneBooks){
                    //so sánh các phần tử với content trong list tạm thời
                    if (phoneBook.getName().toLowerCase(Locale.ROOT).contains(content)){
                        phoneBookArrayList.add(phoneBook);
                    }
                }
               adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        imgdeletepb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtSearch.setText("");
            }
        });

        //back về activity trước như hệ thống dùng onBackPr
        imgbackpb.setOnClickListener(view -> {
//            onBackPressed();
            finish();
        });
    }

    //tạo ra 1 hàm để tạo ra 1 list tạm thời để so sánh các phần tử
    private void buildRecyclerView() {
        //biến tạm thời để hiện ra rv để so sánh
        phoneBookArrayList = new ArrayList<>();
        phoneBookArrayList.add(new PhoneBook("1", "Trần Vân", "0123452345"));
        phoneBookArrayList.add(new PhoneBook("2", "Hồ An", " 012347859"));
        phoneBookArrayList.add(new PhoneBook("3", "Vân Anh", "0321654789"));
        phoneBookArrayList.add(new PhoneBook("4", "Nguyễn Bảo", "01111111454"));
        phoneBookArrayList.add(new PhoneBook("5", "Tấn Sang", "01122334455"));
        phoneBookArrayList.add(new PhoneBook("6", "Ngọc Quỳnh", " 0456789123"));
        phoneBookArrayList.add(new PhoneBook("7", "Thu Thủy", "0456789129"));
        phoneBookArrayList.add(new PhoneBook("8", "Kim Hoa", "078456723"));
        phoneBookArrayList.add(new PhoneBook("9", "Văn Nam", "0984567891"));
        phoneBookArrayList.add(new PhoneBook("10", "Huy Hoàng", "04567666"));

        //biến cố định để lưu
        mPhoneBooks = new ArrayList<>();
        //add tất cả các dữ liệu vào biến cố định sau khi tìm
        mPhoneBooks.addAll(phoneBookArrayList);

        adapter = new PhoneBookAdapter(phoneBookArrayList, FindPhoneBookActivity.this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvListfindpb.setHasFixedSize(true);
        rvListfindpb.setLayoutManager(manager);
        rvListfindpb.setAdapter(adapter);
    }
}
