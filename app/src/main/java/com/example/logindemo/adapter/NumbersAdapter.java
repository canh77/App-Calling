package com.example.logindemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logindemo.R;
import com.example.logindemo.model.Number;

import java.util.List;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumberVH> {
    //number bên model
    List<Number> numbers;
    Context context;

    ImageView img_deletekeyboard, img_googlekeyboard;

    public NumbersAdapter(List<Number> numbers, Context context) {
        this.numbers = numbers;
        this.context = context;

    }

    @NonNull
    @Override
    public NumbersAdapter.NumberVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_number, parent, false);
        return new NumbersAdapter.NumberVH(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull NumbersAdapter.NumberVH holder, int position) {
        Number number = numbers.get(position);
        //cập nhật item với position chạy từ 0
//        if (number == null)
//            return;
        //nếu có dữ liệu
        holder.tvNumber.setText(number.getTitle());
        holder.tvTextNumber.setText(number.getContent());

//        click vào số
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        //số item phụ thuộc vào
        return numbers.size();
    }

    public class NumberVH extends RecyclerView.ViewHolder {
        TextView tvNumber, tvTextNumber;
        LinearLayout lnNumber;

        public NumberVH(View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvTextNumber = itemView.findViewById(R.id.tvTextNumber);
            lnNumber = itemView.findViewById(R.id.lnNumber);
        }
    }


}
