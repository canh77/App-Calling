package com.example.logindemo.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logindemo.R;
import com.example.logindemo.adapter.NumberAdapter;
import com.example.logindemo.model.Number;

import java.util.ArrayList;
import java.util.List;

public class KeyBoardFragment extends Fragment implements NumberAdapter.NumberListener, NumberAdapter.ToastNumber, NumberAdapter.ShowNumber  {
    //import rvKeyboard
    RecyclerView rvKeyboard;
    TextView tvNumberInput;
    ImageView imgCall;


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
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData(view);
        handleEvents(view);
    }

    //xử lí sự kiện
    private void handleEvents(View view) {
        tvNumberInput = view.findViewById(R.id.tvNumberInput);
        imgCall = view.findViewById(R.id.img_call);
    }

    //dùng để khởi tạo
    private void initData(View view) {
//        Log.d("99999999","da vao");
        ArrayList<Number> numbers = new ArrayList<>();
        numbers.add(new Number("1",".."));
        numbers.add(new Number("2","ABC"));
        numbers.add(new Number("3","DEF"));
        numbers.add(new Number("4","GHI"));
        numbers.add(new Number("5","JKL"));
        numbers.add(new Number("6","MNO"));
        numbers.add(new Number("7","PQRS"));
        numbers.add(new Number("8","TUV"));
        numbers.add(new Number("9","WXYZ"));
        numbers.add(new Number("*",""));
        numbers.add(new Number("0","+"));
        numbers.add(new Number("#",""));

        //mapping trong Fragment là phải thêm view trước findViewByID
        rvKeyboard =view.findViewById(R.id.rvKeyboard);
        //khỏi tạo đối tượng adapter //
         numberAdapter = new NumberAdapter(numbers,getContext(),this,this,this);
        //xác định layout
        rvKeyboard.setLayoutManager(new GridLayoutManager(getContext(),3));
        //truyền adapter lên recle view
        rvKeyboard.setAdapter(numberAdapter);
    }

    @Override
    public void update(String number) {

    }

    @Override
    public void toast(String numbers) {
        Toast.makeText(getActivity(), numbers, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void show(String numbers) {
        tvNumberInput.setText(numbers);
    }
}
