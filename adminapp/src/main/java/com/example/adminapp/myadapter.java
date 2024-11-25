package com.example.adminapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Map;


public class myadapter extends FirebaseRecyclerAdapter<Insert2,myadapter.myviewholder>
{      Button btn;
    public myadapter(@NonNull FirebaseRecyclerOptions<Insert2> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Insert2 model)
    {
       holder.name.setText(model.getBookName());
       holder.price.setText(model.getBookPrice()+" Taka");
       holder.category.setText(model.getCategoryId());
      // Glide.with(holder.img.getContext()).load(model.getBookImage()).into(holder.img);
        Picasso.get().load(model.getBookImage()).into(holder.img);


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_layout,parent,false);
       return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
         ImageView img;
        TextView name,price,category;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.img1);
            name=(TextView)itemView.findViewById(R.id.nametext);
            price=(TextView)itemView.findViewById(R.id.prc2text);
            category=(TextView)itemView.findViewById(R.id.ctegtext);
        }
    }
}
