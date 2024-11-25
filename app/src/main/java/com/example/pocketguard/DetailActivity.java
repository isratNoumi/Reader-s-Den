package com.example.pocketguard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
     TextView bookn;
     ImageView img;
     TextView bookpr;
     String tempname,tempimg,tempprc,des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();
        tempname =intent.getStringExtra("BookName");
        tempimg=intent.getStringExtra("BookImage");
        tempprc=intent.getStringExtra("BookPrice");
        des=intent.getStringExtra("description");


        bookn=findViewById(R.id.bookName23);
        img=findViewById(R.id.imagePoster);
        bookpr=findViewById(R.id.bookPrice23);

        if(!tempname.isEmpty() && !tempimg.isEmpty()&& !tempprc.isEmpty())
        {
            bookn.setText(tempname);
            Picasso.get().load(tempimg).into(img);
            bookpr.setText(tempprc);

        }
    }
}