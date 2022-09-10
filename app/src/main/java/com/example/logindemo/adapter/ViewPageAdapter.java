package com.example.logindemo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.logindemo.fragment.KeyBoardFragment;
import com.example.logindemo.fragment.PhoneBookragment;
import com.example.logindemo.fragment.RecentlyFragment;
import com.example.logindemo.model.Recently;

import java.util.List;

public class ViewPageAdapter extends FragmentStateAdapter {

    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return PhoneBookragment.newInstance();
        }else if (position == 2){
            return KeyBoardFragment.newInstance();
        }
        return RecentlyFragment.newInstance();
    }

    @Override
    public int getItemCount() {
        //số trang tạo fragment
        return 3;
    }
}
