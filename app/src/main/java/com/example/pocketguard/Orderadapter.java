package com.example.pocketguard;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class Orderadapter extends FirebaseRecyclerAdapter<Order_Helper,Orderadapter.myviewholder>
{
    public Orderadapter(@NonNull FirebaseRecyclerOptions<Order_Helper> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Order_Helper model)
    {
        holder.id.setText(model.getOrderId());
        holder.price.setText(model.getTotalBill());
        holder.date.setText(model.getOrderdate());
        holder.address.setText(model.getDeliveryaddress());
        // Glide.with(holder.img.getContext()).load(model.getBookImage()).into(holder.img);



    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {

        TextView id,price,address,date;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            id=(TextView)itemView.findViewById(R.id.bookName_t);
            price=(TextView)itemView.findViewById(R.id.totalBill);
            address=(TextView)itemView.findViewById(R.id.del_txt);
            date=(TextView)itemView.findViewById(R.id.datem);
        }
    }
}

