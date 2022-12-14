package com.example.logindemo.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logindemo.AddNumberPhoneActivity;
import com.example.logindemo.ContactUsActivity;
import com.example.logindemo.DetailsPhoneBookActivity;
import com.example.logindemo.FindPhoneBookActivity;
import com.example.logindemo.GarbageCanPBookActivity;
import com.example.logindemo.MainActivity;
import com.example.logindemo.ManagerPhoneBookActivity;
import com.example.logindemo.R;
import com.example.logindemo.SettingPhoneActivity;
import com.example.logindemo.SpeedDialActivity;
import com.example.logindemo.adapter.NumberAdapter;
import com.example.logindemo.adapter.PhoneBookAdapter;
import com.example.logindemo.model.PhoneBook;

import java.util.ArrayList;
import java.util.Locale;

public class PhoneBookragment extends Fragment implements PhoneBookAdapter.UpdateNumber, MainActivity.Test, MainActivity.DisplayNumber, PhoneBookAdapter.DisplaySize {
    RecyclerView rvphonebook;
    ImageView img_search, img_otherpb;
    Intent intent;
    PhoneBookAdapter phoneBookAdapter;
    TextView tvName, tvPhoneNumber, tvSLPhone;

    //khai báo biến toàn cục
    private ArrayList<PhoneBook> phoneBooks;

    private PhoneBookAdapter adapter;

    public PhoneBookragment() {

    }

    //gắn fr vào ac và kiểm tra dữ liệu fragment có trùng với MainAC
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            ((MainActivity) context).setDisplayNumber(this);

        }
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

    //    khởi tạo
    private void initData(View view) {
        phoneBooks = new ArrayList<>();
        phoneBooks.add(new PhoneBook("1", "Trần Vân", "0123452345"));
        phoneBooks.add(new PhoneBook("2", "Hồ An", " 012347859"));
        phoneBooks.add(new PhoneBook("3", "Anh Hải", "0321654789"));
        phoneBooks.add(new PhoneBook("4", "Bá Bảo", "01111111454"));
        phoneBooks.add(new PhoneBook("5", "Tấn Sang", "01122334455"));
        phoneBooks.add(new PhoneBook("6", "Thu Thủy", " 0456789123"));
        phoneBooks.add(new PhoneBook("7", "Huy Hoàng", "0456789129"));
        phoneBooks.add(new PhoneBook("8", "Kim Hoa", "078456723"));
        phoneBooks.add(new PhoneBook("9", "Văn Nam", "0984567891"));
        phoneBooks.add(new PhoneBook("10", "Ngọc Quỳnh", "04567666"));

        //cập nhật lại list cách 1 set vị trí mới
//        phoneBooks.set(2, new PhoneBook("2", "Hồ An2", " 0123478596"));
        adapter = new PhoneBookAdapter(phoneBooks, this.getContext(),this::size);

        //mapping trong fragment
        rvphonebook = view.findViewById(R.id.rvphonebook);
        img_search = view.findViewById(R.id.img_search);
        img_otherpb = view.findViewById(R.id.img_otherpb);
        tvPhoneNumber = view.findViewById(R.id.tvPhoneNumber);
        tvName = view.findViewById(R.id.tvName);
        tvSLPhone = view.findViewById(R.id.tvSLPhone);

        //truyền dữ liệu qua rv
        img_otherpb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getContext().getApplicationContext(), img_otherpb);
                popupMenu.getMenuInflater().inflate(R.menu.menu_phonebook, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.mn_sharepbook:
                                Toast.makeText(view.getContext(), "Chia sẻ danh bạ", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.mn_numberspeedpbook:
                                intent = new Intent(view.getContext(), SpeedDialActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.mn_openviewbeforepbook:
                                Toast.makeText(view.getContext(), "Điện thoại sẽ mở ra màn hình đã xem trước đó", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.mn_managerpbook:
                                intent = new Intent(view.getContext(), ManagerPhoneBookActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.mn_garbagepbook:
                                intent = new Intent(view.getContext(), GarbageCanPBookActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.mn_settingpbook:
                                intent = new Intent(view.getContext(), SettingPhoneActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.contactuspbook:
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

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FindPhoneBookActivity.class);
                startActivity(intent);
            }
        });

        //khởi tạo đối tượng Adapter
        phoneBookAdapter = new PhoneBookAdapter(phoneBooks, getContext(), this, this);

        //xác định layout
        rvphonebook.setLayoutManager(new GridLayoutManager(getContext(), 1));

        //truyền adapter lên RecycleView
        rvphonebook.setAdapter(phoneBookAdapter);

    }

    @Override
    public void update(String number) {
//        String name = tvName.toString();
//        tvName.setText(name);
//        String numberPhone = tvPhoneNumber.toString();
//        tvPhoneNumber.setText(numberPhone);
//        Log.d("///", "update" + tvName + "" + tvPhoneNumber);
    }

    @Override
    public void show() {
        if (rvphonebook.getVisibility() == View.VISIBLE) {
            rvphonebook.setVisibility(View.GONE);
        } else {
            rvphonebook.setVisibility(View.VISIBLE);
        }
    }

    //implement interface
    @Override
    public void display(PhoneBook phoneBook) {
        //add vào phần tử thứ 0
        phoneBooks.add(0, phoneBook);
        //cập nhật lại ArrayList sau khi thêm
        phoneBookAdapter.notifyDataSetChanged();
    }

    //implement interface
    @SuppressLint("SetTextI18n")
    @Override
    public void size(int size) {
        phoneBooks.size();
        tvSLPhone.setText("Danh bạ có "+ size +" số điện thoại");
    }

}
