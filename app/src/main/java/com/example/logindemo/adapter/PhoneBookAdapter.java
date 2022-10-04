package com.example.logindemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logindemo.CallPhoneBookActivity;
import com.example.logindemo.DetailsPhoneBookActivity;
import com.example.logindemo.FindPhoneBookActivity;
import com.example.logindemo.GoogleMeetActivity;
import com.example.logindemo.MainActivity;
import com.example.logindemo.MessengerActivity;
import com.example.logindemo.R;
import com.example.logindemo.SelectedNationalPhoneActivity;
import com.example.logindemo.model.PhoneBook;

import java.util.ArrayList;
import java.util.List;

public class PhoneBookAdapter extends RecyclerView.Adapter<PhoneBookAdapter.PhoneBookVH> {

    //danh bạ model
    List<PhoneBook> phoneBooks;
    Context context;
    String strId, strName, strPhone;

    private int mCurrentSelected = -1;
    private boolean check = true;
    //tạo biến toàn cục UpdateNumber
    private UpdateNumber updateNumber;
    //size
    private DisplaySize displaySize;

    //xóa
    private PhoneBookAdapter phoneBookAdapter;
    private ArrayList<PhoneBook> phoneBookArrayList;

    public PhoneBookAdapter(List<PhoneBook> phoneBooks, Context context, UpdateNumber updateNumber, DisplaySize displaySize) {
        this.phoneBooks = phoneBooks;
        this.context = context;
        this.updateNumber = updateNumber;
        //size
        this.displaySize = displaySize;
    }

    public PhoneBookAdapter(ArrayList<PhoneBook> phoneBooks, Context context, DisplaySize displaySize) {
        //tìm kiếm
        this.phoneBooks = phoneBooks;
        this.context = context;
        this.displaySize = displaySize;
    }

    //Tìm kiếm dữ liệu cập nhật lại adapter
    public void filterList(ArrayList<PhoneBook> filterList) {
        phoneBookArrayList = filterList;
        notifyDataSetChanged();
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

        //tìm kiếm
        holder.tvName.setText(phoneBook.getName());
        holder.tvPhoneNumber.setText(phoneBook.getPhone());

        if (phoneBook == null)
            return;
        if (mCurrentSelected == position) {
            holder.details.setVisibility(View.VISIBLE);
            holder.tvName.setTextColor(Color.BLUE);
        } else {
            holder.details.setVisibility(View.GONE);
            holder.tvName.setTextColor(Color.BLACK);
        }

        //nếu có dữ liệu
        holder.tvName.setText(phoneBook.getName());
        holder.tvPhoneNumber.setText("" + strPhone);

        if (phoneBook.getName() == "Trần Vân") {
            holder.img_id.setImageResource(R.drawable.ic_t);
        } else if (phoneBook.getName() == "Hồ An") {
            holder.img_id.setImageResource(R.drawable.ic_h2);
        } else if (phoneBook.getName() == "Anh Hải") {
            holder.img_id.setImageResource(R.drawable.ic_aphone);
        } else if (phoneBook.getName() == "Bá Bảo") {
            holder.img_id.setImageResource(R.drawable.ic_b);
        } else if (phoneBook.getName() == "Tấn Sang") {
            holder.img_id.setImageResource(R.drawable.ic_t);
        } else if (phoneBook.getName() == "Thu Thủy") {
            holder.img_id.setImageResource(R.drawable.ic_t);
        } else if (phoneBook.getName() == "Huy Hoàng") {
            holder.img_id.setImageResource(R.drawable.ic_h);
        }

        //hiển thị chi tiết khi click vào item
        holder.lntop.setOnClickListener(view -> {
            mCurrentSelected = position;
            notifyDataSetChanged();

            strId = phoneBook.getId();
            strName = phoneBook.getName();
            strPhone = phoneBook.getPhone();
            Log.d("Detail: ", strId + " - " + strPhone);
        });
    }

    @Override
    public int getItemCount() {
        //hiển thị ra số điện thoại
        displaySize.size(phoneBooks.size());
        Log.d("///", "size ");
        return phoneBooks.size();
    }

    public class PhoneBookVH extends RecyclerView.ViewHolder {
        ImageView img_id, imgphonebook, imgmessage, imggooglemeet, imgdetailpb, img_otherpb, img_search, imgdeleteNumber;
        TextView tvName, tvPhoneNumber;
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
            imgphonebook = itemView.findViewById(R.id.imgphonebook);
            imgmessage = itemView.findViewById(R.id.imgmessage);
            imggooglemeet = itemView.findViewById(R.id.imggooglemeet);
            imgdetailpb = itemView.findViewById(R.id.imgdetailpb);
            img_otherpb = itemView.findViewById(R.id.img_otherpb);
            img_search = itemView.findViewById(R.id.img_search);
            imgdeleteNumber = itemView.findViewById(R.id.imgdeleteNumber);


            lnbottom.setOnClickListener(view -> {
                details.setVisibility(View.GONE);
            });

            //xóa số điện thoại trong listphonebook
            imgdeleteNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    phoneBooks.remove(getAdapterPosition());
                    Toast.makeText(context.getApplicationContext(), "Đã xóa " + strName+" thành công ", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            });

            imgphonebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, CallPhoneBookActivity.class);
                    String username = tvName.getText().toString().trim();
                    String phonenum = tvPhoneNumber.getText().toString();
                    intent.putExtra("username", username);
                    intent.putExtra("PhoneNumber", phonenum);
                    context.startActivity(intent);
                }
            });

            imgmessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MessengerActivity.class);
                    String username = tvName.getText().toString().trim();
                    intent.putExtra("username", username);
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
                    String user = tvName.getText().toString().trim();
                    String phone = tvPhoneNumber.getText().toString();
                    intent.putExtra("user", user);
                    intent.putExtra("phone", phone);
                    context.startActivity(intent);
                }
            });
        }
    }

    public interface UpdateNumber {
        void update(String number);
    }

    //hiển thị chuỗi tổng list
    public interface DisplaySize {
        void size(int size);
    }
}
