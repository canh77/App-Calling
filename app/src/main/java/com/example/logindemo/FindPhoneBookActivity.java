package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    Button btnTroLai;

    //giọng nói
    String userToSpeak;
    TextToSpeech t1;

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

        t1 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        imgmicpb.setOnClickListener(view -> {
//            openDialogMic(this);
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hãy nói điều gì đó");
            try {
                startActivityForResult(intent, 101);
            } catch (Exception e) {
                Toast.makeText(this, "Sorry !! you device does not support speech input", Toast.LENGTH_SHORT).show();
            }
        });
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
                for (PhoneBook phoneBook : mPhoneBooks) {
                    //so sánh các phần tử với content trong list tạm thời
                    if (phoneBook.getName().toLowerCase(Locale.ROOT).contains(content)) {
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

        adapter = new PhoneBookAdapter(phoneBookArrayList, FindPhoneBookActivity.this, size -> {
        });
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvListfindpb.setHasFixedSize(true);
        rvListfindpb.setLayoutManager(manager);
        rvListfindpb.setAdapter(adapter);
    }

    //dialog mic speak
    protected void openDialogMic(final Context context) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_mickb);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        btnTroLai = dialog.findViewById(R.id.btnTroLai);
        btnTroLai.setOnClickListener(view -> {
            dialog.dismiss();
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> res = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtSearch.setText(res.get(0));
                    t1.speak(res.get(0), TextToSpeech.QUEUE_FLUSH, null);
                }
        }


    @Override
    public void onDestroy() {
        if (t1 != null) {
            t1.stop();
            t1.shutdown();
        }
        super.onDestroy();
    }
}
