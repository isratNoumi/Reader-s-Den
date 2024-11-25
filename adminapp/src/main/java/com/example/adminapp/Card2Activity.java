package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Card2Activity extends AppCompatActivity {
    RecyclerView recview2;
    myadapter adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card2);
        recview2=(RecyclerView)findViewById(R.id.rec);
        recview2.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Insert2> options =
                new FirebaseRecyclerOptions.Builder<Insert2>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Admincategory"), Insert2.class)
                        .build();

        adapter2=new myadapter(options);
        recview2.setAdapter(adapter2);



    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter2.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter2.stopListening();
    }

    }
