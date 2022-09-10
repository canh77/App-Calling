package com.example.logindemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logindemo.R;
import com.example.logindemo.model.PhoneBook;
import com.example.logindemo.model.Recently;

import java.util.List;

public class RecentlyAdapter extends RecyclerView.Adapter<RecentlyAdapter.RecentlyVH> {
    //gần đây model
    List<Recently> recentlies;
    Context context;

    private int mCurrentSelectedCall = -2;

    public RecentlyAdapter(List<Recently> recentlies, Context context) {
        this.recentlies = recentlies;
        this.context = context;
    }

    @NonNull
    @Override
    public RecentlyAdapter.RecentlyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recently, parent, false);
        return new RecentlyAdapter.RecentlyVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentlyAdapter.RecentlyVH holder, @SuppressLint("RecyclerView") int position) {
        //cập nhật item với position
        Recently recently = recentlies.get(position);


        if (mCurrentSelectedCall == position) {
            holder.tvName_recently.setTextColor(Color.BLACK);
            Log.d("888", "vô " + mCurrentSelectedCall);
            holder.lndetails_recently.setVisibility(View.VISIBLE);
            holder.tvTime.setTextColor(Color.BLUE);
            holder.tvPhoneNumber_recently.setTextColor(Color.BLACK);

        } else {
//            Log.d("888", "chưa vô" + position);
            holder.tvName_recently.setTextColor(Color.BLACK);
            holder.lndetails_recently.setVisibility(View.GONE);
        }

        //nếu có dữ liệu
        holder.tvName_recently.setText(recently.getName());
        holder.tvTime.setText(recently.getTime());
        holder.tvPhoneNumber_recently.setText("Số điện thoại: " + recently.getPhones());
        //set trạng thái điện thoại
        holder.tvCallaway_recently.setText(recently.getSTT(recently.getStatus()));


        if (recently.getStatus() == 0){
            holder.tvCallaway_recently.setTextColor(Color.BLUE);
            holder.img_id_recently.setImageResource(R.drawable.ic_homephone);
//            holder.img_id_recently.setBackgroundColor(Color.BLUE);
        }else if (recently.getStatus() == 1){
            holder.tvCallaway_recently.setTextColor(Color.GRAY);
            holder.img_id_recently.setImageResource(R.drawable.ic_phonego);
//            holder.img_id_recently.setBackgroundColor(Color.GRAY);
        }else if (recently.getStatus() == 2){
            holder.tvCallaway_recently.setTextColor(Color.RED);
            holder.img_id_recently.setImageResource(R.drawable.ic_missedphone);
//            holder.img_id_recently.setBackgroundColor(Color.RED);
        }
        //hiển thị chi tiết khi click vào item
        holder.lntop_recently.setOnClickListener(view -> {
            {
                Log.d("999", "đã vô");
                mCurrentSelectedCall = position;
                //resetl lại dữ liệu
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return recentlies.size();
    }


    public class RecentlyVH extends RecyclerView.ViewHolder {
        TextView tvName_recently, tvPhoneNumber_recently, tvCallaway_recently, tvTime;
        ImageView img_id_recently;
        LinearLayout lnbottom_recentl, lntop_recently, lndetails_recently, lndetail1_recently, lndetail2_recently;


        public RecentlyVH(@NonNull View itemView) {
            super(itemView);

            tvPhoneNumber_recently = itemView.findViewById(R.id.tvPhoneNumber_recently);
            tvName_recently = itemView.findViewById(R.id.tvName_recently);
            tvCallaway_recently = itemView.findViewById(R.id.tvCallaway_recently);
            tvTime = itemView.findViewById(R.id.tvTime_recently);
            img_id_recently = itemView.findViewById(R.id.imgid_recently);
            lnbottom_recentl = itemView.findViewById(R.id.lnbottom_recently);
            lntop_recently = itemView.findViewById(R.id.lntop_recently);
            lndetails_recently = itemView.findViewById(R.id.lndetails_recently);
            lndetail1_recently = itemView.findViewById(R.id.lndetail1_recently);
            lndetail2_recently = itemView.findViewById(R.id.lndetail2_recently);

        }
    }
}
