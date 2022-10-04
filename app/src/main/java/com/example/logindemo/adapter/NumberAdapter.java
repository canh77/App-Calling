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

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberVH> {
    //number bên model
    List<Number> numbers;
    Context context;

    private NumberListener numberListener;
    private ToastNumber toastNumber;
    //triển khai sự kiên trong interface
    private ShowNumber showNumber;
    //    TextView tvNumber;
    ImageView img_deletekeyboard, img_googlekeyboard;

    public NumberAdapter(List<Number> numbers, Context context, NumberAdapter.NumberListener numberListener, NumberAdapter.ToastNumber toastNumber, NumberAdapter.ShowNumber showNumber) {
        this.numbers = numbers;
        this.context = context;
        this.numberListener = numberListener;
        this.toastNumber = toastNumber;
        this.showNumber = showNumber;
    }

    @NonNull
    @Override
    public NumberAdapter.NumberVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_number, parent, false);
        return new NumberAdapter.NumberVH(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull NumberAdapter.NumberVH holder, int position) {
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
//              toastNumber.toast(number.getTitle());
                showNumber.show(number.getTitle());
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

    //tạo ra 1 interface
    public interface NumberListener {
        void update(String number);
    }

    public interface ToastNumber {
        void toast(String numbers);
    }

    //click vào number hiện lên textview
    public interface ShowNumber {
        void show(String numbers);
    }
}
