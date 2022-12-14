package com.example.logindemo.fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logindemo.ContactUsActivity;
import com.example.logindemo.FindPhoneBookActivity;
import com.example.logindemo.GoogleMeetActivity;
import com.example.logindemo.R;
import com.example.logindemo.SettingPhoneActivity;
import com.example.logindemo.SpeedDialActivity;
import com.example.logindemo.adapter.NumberAdapter;
import com.example.logindemo.model.Number;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class KeyBoardFragment extends Fragment implements NumberAdapter.NumberListener, NumberAdapter.ToastNumber, NumberAdapter.ShowNumber {
    //import rvKeyboard
    RecyclerView rvKeyboard;
    ImageView imgCallkb, img_otherkeyboard, img_search, img_googlekeyboard, img_deletekeyboard;
    Intent intent;

    private static final int REQUEST_CALL = 1;
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

    //x??? l?? s??? ki???n
    private void handleEvents(View view) {

    }

    //d??ng ????? kh???i t???o
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

        //mapping trong Fragment l?? ph???i th??m view tr?????c findViewByID
        rvKeyboard = view.findViewById(R.id.rvKeyboard1);
        img_search = view.findViewById(R.id.img_search);
        edNumberInput = view.findViewById(R.id.edNumberInput);
        imgCallkb = view.findViewById(R.id.img_callkb);

        imgCallkb.setOnClickListener(view1 -> {
            makePhoneCall();
        });

//        imgCallkb.setOnClickListener(view1 -> {
//            //??i???u ki???n n???u ko nh???p
//            if (phoneNumbers.equals("")) {
//                Toast.makeText(getContext(), "B???n ph???i nh???p v??o s??? ??i???n tho???i", Toast.LENGTH_SHORT).show();
//            } else {
////               String edNumberInput = " ";
//                Intent intent = new Intent(view.getContext(), CallPhoneBookActivity.class);
//                intent.putExtra("PhoneNumber", phoneNumbers);
//                phoneNumbers = "";
//                edNumberInput.getText().clear();
//                startActivity(intent);
//            }
//        });

        img_deletekeyboard = view.findViewById(R.id.img_deletekeyboard);
        img_googlekeyboard = view.findViewById(R.id.img_googlekeyboard);
        img_googlekeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), GoogleMeetActivity.class);
                startActivity(intent);
            }
        });

//        nh???n gi??? n??t x??a
            img_deletekeyboard.setOnLongClickListener(view1 -> {
            phoneNumbers = "";
            edNumberInput.setText(phoneNumbers);
            img_deletekeyboard.setVisibility(View.GONE);
            img_googlekeyboard.setVisibility(View.GONE);
            return false;
        });

        //x??a t???ng item
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

        //X??? l?? ko cho hi???n b??n ph??m
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
                                Toast.makeText(getContext(), "??i???n tho???i s??? lu??n m??? v???i ch??? ????? xem b??n ph??m", Toast.LENGTH_SHORT).show();
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


        numberAdapter = new NumberAdapter(numbers,getContext(),this,this,this);
        //x??c ?????nh layout
        rvKeyboard.setLayoutManager(new GridLayoutManager(getContext(), 3));
        //truy???n adapter l??n rycleview
        rvKeyboard.setAdapter(numberAdapter);
    }

    //g???i ??i???n tho???i
    private void makePhoneCall() {
        String number = edNumberInput.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(this.getContext(),
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this.getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                //tr??? v??? chu???i r???ng sau khi nh???p s??? ??i???n tho???i
                phoneNumbers = "";
                edNumberInput.getText().clear();
            }
        } else {
            Toast.makeText(this.getContext(), "H??y nh???p s??? ??i???n tho???i", Toast.LENGTH_SHORT).show();
        }
    }

    //override cho ph????ng th???c c???p quy???n
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL){
            if (grantResults.length>0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED);
            makePhoneCall();
        }else {
            Toast.makeText(this.getContext(), "Permisson DENIED", Toast.LENGTH_SHORT).show();
        }
    }

        private boolean isValidMobile(String phoneNumbers) {
        boolean check = false;
        if (!Pattern.matches("^[0-9]$", phoneNumbers)) {
            if (phoneNumbers.length() < 10 || phoneNumbers.length() > 10) {
                check = false;
                Toast.makeText(getContext(), "S??? ??i???n tho???i kh??ng h???p l???", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "S??? ??i???n tho???i l???", Toast.LENGTH_SHORT).show();
            }
        } else {
            check = false;
        }
        return check;
    }

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
