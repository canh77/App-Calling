package com.example.logindemo.fragment;


import static android.content.Context.INPUT_METHOD_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logindemo.CallPhoneBookActivity;
import com.example.logindemo.ContactUsActivity;
import com.example.logindemo.FindPhoneBookActivity;
import com.example.logindemo.GoogleMeetActivity;
import com.example.logindemo.R;
import com.example.logindemo.SettingPhoneActivity;
import com.example.logindemo.SpeedDialActivity;
import com.example.logindemo.adapter.NumberAdapter;
import com.example.logindemo.model.Number;
import com.example.logindemo.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyBoardFragment extends Fragment implements NumberAdapter.NumberListener, NumberAdapter.ToastNumber, NumberAdapter.ShowNumber {
    //import rvKeyboard
    RecyclerView rvKeyboard;
    ImageView imgCallkb, img_otherkeyboard, img_search, img_googlekeyboard, img_deletekeyboard;
    Intent intent;

    private EditText edNumberInput;
    String phoneNumbers = "";

    NumberAdapter numberAdapter;

    public KeyBoardFragment() {
        // Required empty public constructor
    }


    public static KeyBoardFragment newInstance() {
        KeyBoardFragment fragment = new KeyBoardFragment();
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
        return inflater.inflate(R.layout.fragment_banphim, container, false);
    }

    //override onViewCreated
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData(view);
        handleEvents(view);
    }

    //xử lí sự kiện
    private void handleEvents(View view) {

    }


    //dùng để khởi tạo
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initData(View view) {
//        Log.d("99999999","da vao");
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

        //mapping trong Fragment là phải thêm view trước findViewByID
        rvKeyboard = view.findViewById(R.id.rvKeyboard);
        img_search = view.findViewById(R.id.img_search);
        edNumberInput = view.findViewById(R.id.edNumberInput);
        imgCallkb = view.findViewById(R.id.img_callkb);

        imgCallkb.setOnClickListener(view1 -> {
            //điều kiện nếu ko nhập
            if (phoneNumbers.equals("")) {
                Toast.makeText(getContext(), "Bạn phải nhập vào số điện thoại", Toast.LENGTH_SHORT).show();
            } else {
//               String edNumberInput = " ";
                Intent intent = new Intent(view.getContext(), CallPhoneBookActivity.class);
                intent.putExtra("PhoneNumber", phoneNumbers);
                phoneNumbers = "";
                edNumberInput.getText().clear();
                startActivity(intent);
            }
        });

        img_deletekeyboard = view.findViewById(R.id.img_deletekeyboard);
        img_googlekeyboard = view.findViewById(R.id.img_googlekeyboard);

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
                Intent intent = new Intent(getContext().getApplicationContext(), FindPhoneBookActivity.class);
                startActivity(intent);
            }
        });

        img_otherkeyboard = view.findViewById(R.id.img_otherkeyboard);
        img_otherkeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getContext().getApplicationContext(), img_otherkeyboard);
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
                                Toast.makeText(getContext(), "Điện thoại sẽ luôn mở với chế độ xem bàn phím", Toast.LENGTH_SHORT).show();
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

        numberAdapter = new NumberAdapter(numbers, getContext(), this, this, this);
        //xác định layout
        rvKeyboard.setLayoutManager(new GridLayoutManager(getContext(), 3));
        //truyền adapter lên rycleview
        rvKeyboard.setAdapter(numberAdapter);
    }

//    private boolean isValidMobile(String phoneNumbers) {
//        boolean check = false;
//        if (!Pattern.matches("^[0-9]$", phoneNumbers)) {
//            if (phoneNumbers.length() < 10 || phoneNumbers.length() > 10) {
//                check = false;
//                Toast.makeText(getContext(), "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getContext(), "Số điện thoại lệ", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            check = false;
//        }
//        return check;
//    }

    @Override
    public void update(String number) {
    }

    @Override
    public void toast(String numbers) {
        //   gitt
        Toast.makeText(getActivity(), numbers, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void show(String numbers) {
        img_deletekeyboard.setVisibility(View.VISIBLE);
        img_googlekeyboard.setVisibility(View.VISIBLE);
        phoneNumbers = phoneNumbers + numbers;
        edNumberInput.setText(phoneNumbers);
        Log.d("/////", "phoneNumber: " + phoneNumbers);
    }
}
