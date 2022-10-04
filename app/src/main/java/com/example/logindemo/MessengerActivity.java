package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logindemo.adapter.ChatAppMsgAdapter;
import com.example.logindemo.adapter.MessageAdapter;
import com.example.logindemo.fragment.KeyBoardFragment;
import com.example.logindemo.model.ChatAppMsgDTO;
import com.example.logindemo.model.Message;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MessengerActivity extends AppCompatActivity {
    ImageView imgiconsmsggone, ivLoadImage, imgsendmsggone, imgiconmsvisible, imgbackmsg, imgsendmsg, imgiconmsg, imgsimmsg, imgmicromsg, imgothersmsg, imgphonemsg, imgcameramsg, imgaddmsg, imgfindmsg;
    LinearLayout lnTextInput, lnstatus, lnnewstatus, lnnewTextInput;
    Intent intent;
    ImageView ln2;
    RecyclerView rvMessage;
    MessageAdapter messageAdapter;
    TextView tvMessage,tvNameUsermsg;

    //thu âm giọng nói
    EditText edTextInput;
    String userToSpeak;
    TextToSpeech t1;

    // Load ảnh
    Uri imageUri;
    private View mRootView;

    //nhắn tin
    RecyclerView rvTinNhan;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);


        imgbackmsg = findViewById(R.id.imgbackmsg);
        imgiconmsvisible = findViewById(R.id.imgiconmsvisible);
        imgmicromsg = findViewById(R.id.imgmicromsg);
        imgiconsmsggone = findViewById(R.id.imgiconsmsggone);
        imgsendmsggone = findViewById(R.id.imgsendmsggone);
        imgsimmsg = findViewById(R.id.imgsimmsg);
        edTextInput = findViewById(R.id.edTextInput);
        imgothersmsg = findViewById(R.id.imgothersmsg);
        imgcameramsg = findViewById(R.id.imgcameramsg);
        imgaddmsg = findViewById(R.id.imgaddmsg);
        imgfindmsg = findViewById(R.id.imgfindmsg);
        imgphonemsg = findViewById(R.id.imgphonemsg);
        ln2 = findViewById(R.id.ln2);
        rvMessage = findViewById(R.id.rvMessage);
        rvTinNhan = findViewById(R.id.rvTinNhan);
        imgiconsmsggone = findViewById(R.id.imgiconsmsggone);
        tvNameUsermsg = findViewById(R.id.tvNameUsermsg);

        //nhận dữ liệu phải dùng chung key để nhận và truyền dữ liệu
        String name = getIntent().getStringExtra("username");
        tvNameUsermsg.setText(name);

        t1 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        //edit text mic
        imgiconsmsggone.setOnClickListener(view -> {
            userToSpeak = edTextInput.getText().toString();
            if (userToSpeak.equals("")) {
                Toast.makeText(this, "Please enter your text", Toast.LENGTH_SHORT).show();
            } else {
//                Toast.makeText(this, userToSpeak, Toast.LENGTH_SHORT).show();
                t1.speak(userToSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        //mic
        imgmicromsg.setOnClickListener(view -> {
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

        //chuyển từ activity sang fragments
        imgphonemsg.setOnClickListener(view -> {
            Intent intent = new Intent(MessengerActivity.this, BoardCallActivity.class);
            startActivity(intent);
        });

        imgfindmsg.setOnClickListener(view -> {
            Intent intent = new Intent(MessengerActivity.this, FindMessageActivity.class);
            startActivity(intent);

//           hideKeyboard();
//            showKeyboard();

        });

        imgaddmsg.setOnClickListener(view -> {
            ArrayList<Message> messages = new ArrayList<>();
            messages.add(new Message("0", "ảnh GIF"));
            messages.add(new Message("1", "Hình dán"));
            messages.add(new Message("2", "Tệp"));
            messages.add(new Message("3", "Vị trí"));
            messages.add(new Message("4", "Danh bạ"));
            messages.add(new Message("5", "Gửi theo lịch biểu"));
            messageAdapter = new MessageAdapter(messages, getApplication());
            rvMessage.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
            rvMessage.setAdapter(messageAdapter);
            imgaddmsg.setOnClickListener(view1 -> {
                if (rvMessage.getVisibility() == View.VISIBLE) {
                    rvMessage.setVisibility(View.GONE);
                } else {
                    rvMessage.setVisibility(View.VISIBLE);
                }
            });
        });

        imgcameramsg.setOnClickListener(view -> {
            openGallery();
        });

        imgothersmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(getApplicationContext(), imgothersmsg);
                menu.getMenuInflater().inflate(R.menu.menu_message, menu.getMenu());
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.mn_details:
                                intent = new Intent(getApplicationContext(), DetailsMsgActivity.class);
                                String name = tvNameUsermsg.getText().toString().trim();
                                intent.putExtra("user", name);
                                startActivity(intent);
                                finish();
                                break;
                            case R.id.mn_save:
                                intent = new Intent(getApplicationContext(), SettingPhoneActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                            case R.id.mn_detail:
                                intent = new Intent(getApplicationContext(), ContactUsActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                        }
                        return true;
                    }
                });
                menu.show();
            }
        });
        // Set RecyclerView layout manager.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvTinNhan.setLayoutManager(linearLayoutManager);

        // Create the initial data list.
        final List<ChatAppMsgDTO> msgDtoList = new ArrayList<>();

        ChatAppMsgDTO msgDto = new ChatAppMsgDTO(ChatAppMsgDTO.MSG_TYPE_RECEIVED, "Hello!!",null);
        ChatAppMsgDTO msgDto1 = new ChatAppMsgDTO(ChatAppMsgDTO.MSG_TYPE_RECEIVED,"How are you to day?",null);

        msgDtoList.add(msgDto);
        msgDtoList.add(msgDto1);
        // Create the data adapter with above data list.
        final ChatAppMsgAdapter chatAppMsgAdapter = new ChatAppMsgAdapter(msgDtoList);

        // Set data adapter to RecyclerView.
        rvTinNhan.setAdapter(chatAppMsgAdapter);

        edTextInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    imgiconsmsggone.setVisibility(View.GONE);
                    imgsendmsggone.setVisibility(View.GONE);
                    imgiconmsvisible.setVisibility(View.VISIBLE);
                    imgsimmsg.setVisibility(View.VISIBLE);
                    imgmicromsg.setVisibility(View.VISIBLE);
                } else {
                    imgiconsmsggone.setVisibility(View.VISIBLE);
                    imgsendmsggone.setVisibility(View.VISIBLE);
                    imgiconmsvisible.setVisibility(View.GONE);
                    imgsimmsg.setVisibility(View.GONE);
                    imgmicromsg.setVisibility(View.GONE);
                    rvMessage.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        imgsendmsggone.setOnClickListener(view -> {
            String msgContent = edTextInput.getText().toString();
            if(!TextUtils.isEmpty(msgContent))
            {
                // Add a new sent message to the list.
                ChatAppMsgDTO msg = new ChatAppMsgDTO(ChatAppMsgDTO.MSG_TYPE_SENT, msgContent,null
                );
                msgDtoList.add(msg);

                int newMsgPosition = msgDtoList.size() - 1;

                // Notify recycler view insert one new data.
                chatAppMsgAdapter.notifyItemInserted(newMsgPosition);

                // Scroll RecyclerView to the last message.
                rvTinNhan.scrollToPosition(newMsgPosition);

                // Empty the input edit text box.
                edTextInput.setText("");
            }
        });

    }

    public void cancelPhoneBook(View view) {
        Intent intent = new Intent(MessengerActivity.this, MainActivity.class);
        startActivity(intent);
        onBackPressed();
    }

    public void goGgmeet(View view) {
        Intent intent = new Intent(MessengerActivity.this, GoogleMeetActivity.class);
        startActivity(intent);
    }

    //load ảnh trong thư viện
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //lấy ảnh

        //chuyển đổi giọng nói thành văn bản
        switch (requestCode) {
            case 100: {
                if (resultCode == RESULT_OK) {
                    imageUri = data.getData();
                    ln2.setImageURI(imageUri);
                }
                break;
            }
            case 101: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> res = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edTextInput.setText(res.get(0));
                    t1.speak(res.get(0), TextToSpeech.QUEUE_FLUSH, null);
                }
                break;
            }
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

    //ẩn bàn phím
    private void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //hiện bàn phím
    private void showKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

}
