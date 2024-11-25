package com.example.pocketguard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

     public TextView CategoryName;
     public ImageView logo;
     public  RecyclerView categoryrecycleview;
     public RecyclerView.LayoutManager manager;
    public CategoryViewHolder(@NonNull  View itemView) {
        super(itemView);
        manager=new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false);

        CategoryName=itemView.findViewById(R.id.category_name);
        logo=itemView.findViewById(R.id.imageView1);
        categoryrecycleview=itemView.findViewById(R.id.my_recycler);
        categoryrecycleview.setLayoutManager(manager);

    }
}
