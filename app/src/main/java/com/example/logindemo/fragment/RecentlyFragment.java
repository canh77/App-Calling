package com.example.logindemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logindemo.R;
import com.example.logindemo.adapter.RecentlyAdapter;
import com.example.logindemo.model.Recently;

import java.util.ArrayList;

public class RecentlyFragment extends Fragment {
    //import rvrecently vào
    RecyclerView rvrecently;

    public RecentlyFragment() {
        // Required empty public constructor
    }

    public static RecentlyFragment newInstance() {
        RecentlyFragment fragment = new RecentlyFragment();
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
        return inflater.inflate(R.layout.fragment_ganday, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //khởi tạo
        initData(view);
        //bắt sự kiện
        handleEvents(view);

    }

    private void handleEvents(View view) {

    }

    //khổiƯ tạo giá trị
    private void initData(View view) {
        ArrayList<Recently> recentlies = new ArrayList<>();
        recentlies.add(new Recently("1", "Trần Vân", "0123452345", 1, "10:15"));
        recentlies.add(new Recently("2", "Hồ An", " 012347859", 0, "1:03"));
        recentlies.add(new Recently("3", "Vân Anh", " 0321654789", 2, "2:15"));
        recentlies.add(new Recently("4", "Nguyễn Bảo", "01111111454", 1, "12:00"));
        recentlies.add(new Recently("5", "Tấn Sang", "01122334455", 1, "14:30"));
        recentlies.add(new Recently("6", "Ngọc Quỳnh", " 0456789123", 0, "22:00"));
        recentlies.add(new Recently("7", "Thu Thủy", "0456789129", 0,"23:45"));
        recentlies.add(new Recently("8", "Kim Hoa", " 078456723", 1, "12:34"));
        recentlies.add(new Recently("9", "Văn Nam", "0984567891", 0, "21:12"));
        recentlies.add(new Recently("10", "Huy Hoàng", "04567666", 2,"6:58"));

        //mapping trong fragment
        rvrecently = view.findViewById(R.id.rvrecently);

        //Khởi tạo đối tượng adapter
        RecentlyAdapter recentlyAdapter = new RecentlyAdapter(recentlies,getContext());

        //xác định layout manager
        rvrecently.setLayoutManager(new GridLayoutManager(getContext(),1));

        //Truyền adapter lên Recycle view
        rvrecently.setAdapter(recentlyAdapter);

    }
}
