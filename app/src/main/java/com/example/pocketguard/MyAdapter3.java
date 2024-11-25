package com.example.pocketguard;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.MyViewHolder>{
    private PaymentActivity activity;
    private List<Model> mList;

    int totalPrice1 =0;
    // int Shippingcrg=100;

    private FirebaseFirestore firestore= FirebaseFirestore.getInstance();
    FirebaseAuth auth;

    public MyAdapter3(PaymentActivity activity, List<Model> mList){
        this.activity = activity;
        this.mList= mList;
        auth = FirebaseAuth.getInstance();
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.item , parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.BookName.setText(mList.get(position).getBookName());
        holder.totalQuantity.setText(mList.get(position).getTotalQuantity());
        holder.totalPrice.setText(String.valueOf(mList.get(position).getTotalPrice()));


        totalPrice1= totalPrice1 +mList.get(position).getTotalPrice();
        Intent intent =new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",totalPrice1);
        LocalBroadcastManager.getInstance(activity).sendBroadcast(intent);


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView BookName,totalQuantity,totalPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            BookName = itemView.findViewById(R.id.bookName_txt);
            totalQuantity = itemView.findViewById(R.id.totalQuantity_txt);
            totalPrice = itemView.findViewById(R.id.totalPrice_txt);


        }
    }
}


