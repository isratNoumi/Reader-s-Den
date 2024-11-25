package com.example.pocketguard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;


public class myadapter extends FirebaseRecyclerAdapter<Category2,myadapter.myviewholder>
{
    public myadapter(@NonNull FirebaseRecyclerOptions<Category2> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Category2 model)
    {
       holder.name.setText(model.getBookName());
       holder.price.setText(model.getBookPrice());
       //holder.category.setText(model.getCategoryId());
      // Glide.with(holder.img.getContext()).load(model.getBookImage()).into(holder.img);
        Picasso.get().load(model.getBookImage()).into(holder.img);


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
       return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
         ImageView img;
        TextView name,price,category;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.profile_image);
            name=(TextView)itemView.findViewById(R.id.name_text);
            price=(TextView)itemView.findViewById(R.id.status_text);
            //category=(TextView)itemView.findViewById(R.id.ctegtext);
        }
    }
}
