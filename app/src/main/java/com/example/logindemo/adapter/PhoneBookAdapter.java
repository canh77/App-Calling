package com.example.logindemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logindemo.CallPhoneBookActivity;
import com.example.logindemo.DetailsPhoneBookActivity;
import com.example.logindemo.GoogleMeetActivity;
import com.example.logindemo.MainActivity;
import com.example.logindemo.MessengerActivity;
import com.example.logindemo.R;
import com.example.logindemo.SelectedNationalPhoneActivity;
import com.example.logindemo.model.PhoneBook;

import java.util.List;

public class PhoneBookAdapter extends RecyclerView.Adapter<PhoneBookAdapter.PhoneBookVH> {

    //danh ba model
    List<PhoneBook> phoneBooks;
    Context context;
    String strId, strName, strPhone;

    private int mCurrentSelected = -2;

    public PhoneBookAdapter(List<PhoneBook> phoneBooks, Context context) {
        this.phoneBooks = phoneBooks;
        this.context = context;
    }

    @NonNull
    @Override
    public PhoneBookAdapter.PhoneBookVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_phonebook, parent, false);
        return new PhoneBookAdapter.PhoneBookVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneBookAdapter.PhoneBookVH holder, @SuppressLint("RecyclerView") int position) {
        //cập nhật item với positon
        PhoneBook phoneBook = phoneBooks.get(position);

        if (phoneBook == null) return;

        if (mCurrentSelected == position){
            holder.tvName.setTextColor(Color.BLUE);
            holder.details.setVisibility(View.VISIBLE);
        }else {
            holder.tvName.setTextColor(Color.BLACK);
            holder.details.setVisibility(View.GONE);
        }

        //nếu có dữ liệu
        holder.tvName.setText(phoneBook.getName());
        holder.tvPhoneNumber.setText("Điện thoại: "+strPhone);

        //hiển thị chi tiết khi click vào item
        holder.lntop.setOnClickListener(view -> {
            mCurrentSelected = position;
            notifyDataSetChanged();

            strId = phoneBook.getId();
            strName = phoneBook.getName();
            strPhone = phoneBook.getPhone();

            Log.d("Detail: ",strId +" - "+ strPhone);

//            holder.tvPhoneNumber.setText("Điện thoại: "+strPhone);

        });

//        holder.img_id.setOnClickListener(view -> {
//            holder.detail1.setVisibility(View.VISIBLE);
//            holder.detail2.setVisibility(View.VISIBLE);
//        });

    }

    @Override
    public int getItemCount() {
        //số item phụ thuộc vào size
        return phoneBooks.size();
    }


    public class PhoneBookVH extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView img_id,imgphone,imgmessage,imggooglemeet,imgdetailpb;
        TextView tvPhoneNumber;
        LinearLayout details, lnbottom, lntop, detail1, detail2;


        public PhoneBookVH(@NonNull View itemView) {
            super(itemView);
            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);

            details = itemView.findViewById(R.id.details);
            lnbottom = itemView.findViewById(R.id.lnbottom);
            lntop = itemView.findViewById(R.id.lntop);
            detail1 = itemView.findViewById(R.id.detail1);
            detail2 = itemView.findViewById(R.id.detail2);

            tvName = itemView.findViewById(R.id.tvName);
            img_id = itemView.findViewById(R.id.img_id);
            imgphone = itemView.findViewById(R.id.imgphone);
            imgmessage = itemView.findViewById(R.id.imgmessage);
            imggooglemeet = itemView.findViewById(R.id.imggooglemeet);
            imgdetailpb = itemView.findViewById(R.id.imgdetailpb);


            imgphone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, CallPhoneBookActivity.class);
                    context.startActivity(intent);
                }
            });

            imgmessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MessengerActivity.class);
                    context.startActivity(intent);
                }
            });

            imggooglemeet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, GoogleMeetActivity.class);
                    context.startActivity(intent);
                }
            });

            imgdetailpb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailsPhoneBookActivity.class);
                    context.startActivity(intent);
                }
            });






        }


//        public void resetLayout() {
//            detail1.setVisibility(View.GONE);
//            detail2.setVisibility(View.GONE);
//        }
    }
}
