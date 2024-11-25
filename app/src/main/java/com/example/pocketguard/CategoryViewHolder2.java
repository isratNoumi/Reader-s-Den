package com.example.pocketguard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder2  extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView bookimage;
    public TextView bookname;
    public TextView bookprice;
    public  SubCategory subCategory;

    public CategoryViewHolder2(@NonNull  View itemView) {
        super(itemView);
        bookimage=itemView.findViewById(R.id.image);
        bookname=itemView.findViewById(R.id.title);
        bookprice=itemView.findViewById(R.id.price);
        itemView.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
     subCategory.onClick(v,false);
    }
    public  void SubCategory(SubCategory subCategory)
    {
        this.subCategory=subCategory;
    }
}
