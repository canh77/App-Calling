package com.example.logindemo.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logindemo.AddNumberPhoneActivity;
import com.example.logindemo.MainActivity;
import com.example.logindemo.R;
import com.example.logindemo.adapter.PhoneBookAdapter;
import com.example.logindemo.model.PhoneBook;

import java.util.ArrayList;

public class PhoneBookragment extends Fragment {
    //import rvPhonebook
    RecyclerView rvphonebook;

    public PhoneBookragment() {

    }

    public static PhoneBookragment newInstance() {
        PhoneBookragment fragment = new PhoneBookragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_danhba, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData(view);
        handleEvents(view);

    }

    //xử lí sự kiện
    private void handleEvents(View view) {
    }

    //khởi tạo
    private void initData(View view) {
        ArrayList<PhoneBook> phoneBooks = new ArrayList<>();
        phoneBooks.add(new PhoneBook("1", "Trần Vân", "0123452345"));
        phoneBooks.add(new PhoneBook("2", "Hồ An", " 012347859"));
        phoneBooks.add(new PhoneBook("3", "Vân Anh",  "0321654789"));
        phoneBooks.add(new PhoneBook("4", "Nguyễn Bảo", "01111111454"));
        phoneBooks.add(new PhoneBook("5", "Tấn Sang", "01122334455"));
        phoneBooks.add(new PhoneBook("6", "Ngọc Quỳnh", " 0456789123"));
        phoneBooks.add(new PhoneBook("7", "Thu Thủy", "0456789129"));
        phoneBooks.add(new PhoneBook("8", "Kim Hoa", "078456723"));
        phoneBooks.add(new PhoneBook("9", "Văn Nam", "0984567891"));
        phoneBooks.add(new PhoneBook("10", "Huy Hoàng", "04567666"));


        //mapping trong fragment
        rvphonebook = view.findViewById(R.id.rvphonebook);

        //khởi tạo đối tượng Adapter
        PhoneBookAdapter phoneBookAdapter = new PhoneBookAdapter(phoneBooks, getContext());

        //xác định layout
        rvphonebook.setLayoutManager(new GridLayoutManager(getContext(), 1));

        //truyền adapter lên RecycleView
        rvphonebook.setAdapter(phoneBookAdapter);

    }

}
