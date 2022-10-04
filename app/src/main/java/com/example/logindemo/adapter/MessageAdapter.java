package com.example.logindemo.adapter;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logindemo.R;
import com.example.logindemo.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageVH> {

    List<Message> messages;
    Context context;
    MessageAdapter messageAdapter;

    public MessageAdapter(List<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageAdapter.MessageVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msg, parent, false);
        return new MessageAdapter.MessageVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MessageVH holder, int position) {
        Message message = messages.get(position);
        holder.tvTextmsg.setText(message.getTitle());
        if (message.getTitle() == "ảnh GIF") {
            holder.ivmsg1.setImageResource(R.drawable.ic_gif);
        } else if (message.getTitle() == "Vị trí") {
            holder.ivmsg1.setImageResource(R.drawable.ic_location);
        } else if (message.getTitle() == "Hình dán") {
            holder.ivmsg1.setImageResource(R.drawable.ic_nhandan);
        } else if (message.getTitle() == "Tệp") {
            holder.ivmsg1.setImageResource(R.drawable.ic_attach);
        } else if (message.getTitle() == "Danh bạ") {
            holder.ivmsg1.setImageResource(R.drawable.ic_danhba);
        } else if (message.getTitle() == "Gửi theo lịch biểu") {
            holder.ivmsg1.setImageResource(R.drawable.ic_time);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageVH extends RecyclerView.ViewHolder {
        TextView tvTextmsg;
        ImageView ivmsg1;
        LinearLayout lnmsg;
        RecyclerView rvMessage;

        public MessageVH(View itemView) {
            super(itemView);
            tvTextmsg = itemView.findViewById(R.id.tvTextmsg);
            ivmsg1 = itemView.findViewById(R.id.ivmsg1);
            lnmsg = itemView.findViewById(R.id.lnmsg);
            rvMessage = itemView.findViewById(R.id.rvMessage);
            ivmsg1.setOnClickListener(view -> {
//                openDialogCalender();
            });
        }

    }

//    protected  void openDialogCalender(){
//        Dialog dialog = new Dialog(context);
//        dialog.setContentView(R.layout.dialogcalender);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.show();
//    }
}
