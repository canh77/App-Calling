package com.example.logindemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logindemo.R;
import com.example.logindemo.model.ChatAppMsgDTO;

import java.util.List;

public class ChatAppMsgAdapter extends RecyclerView.Adapter<ChatAppMsgAdapter.ChatAppMsgVH> {
    private List<ChatAppMsgDTO> msgDtoList = null;

    public ChatAppMsgAdapter(List<ChatAppMsgDTO> msgDtoList) {
        this.msgDtoList = msgDtoList;
    }

    @NonNull
    @Override
    public ChatAppMsgVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chatmsg, parent, false);
        return new ChatAppMsgAdapter.ChatAppMsgVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAppMsgAdapter.ChatAppMsgVH holder, int position) {
        ChatAppMsgDTO msgDto = this.msgDtoList.get(position);
        // If the message is a received message.
        if(msgDto.MSG_TYPE_RECEIVED.equals(msgDto.getMsgType()))
        {
            // Show received message in left linearlayout.
            holder.lnLeft.setVisibility(LinearLayout.VISIBLE);
            holder.tvLeft.setText(msgDto.getMsgContent());
            holder.lnRight.setVisibility(LinearLayout.GONE);
        }
        // If the message is a sent message.
        else if(msgDto.MSG_TYPE_SENT.equals(msgDto.getMsgType()))
        {
            // Show sent message in right linearlayout.
            holder.lnRight.setVisibility(LinearLayout.VISIBLE);
            holder.tvRight.setText(msgDto.getMsgContent());
            holder.lnLeft.setVisibility(LinearLayout.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return msgDtoList.size();
    }

    public class ChatAppMsgVH extends RecyclerView.ViewHolder {
        LinearLayout lntextmsg;
        LinearLayout lnLeft;
        LinearLayout lnRight;
        TextView tvLeft;
        TextView tvRight;
        LinearLayout lnImageRight;
        ImageView imgRight;

        public ChatAppMsgVH(View itemView) {
            super(itemView);

            if (itemView != null) {
                lntextmsg = itemView.findViewById(R.id.lntextmsg);
                lnLeft =  itemView.findViewById(R.id.lnLeft);
                lnRight =  itemView.findViewById(R.id.lnRight);
                tvLeft = itemView.findViewById(R.id.tvLeft);
                tvRight = itemView.findViewById(R.id.tvRight);
                lnImageRight = itemView.findViewById(R.id.lnImageRight);
                imgRight = itemView.findViewById(R.id.imgRight);
            }
        }
    }
}
