package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Card4Activity extends AppCompatActivity {
     Button btn;
    RecyclerView recview;
    Edit_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card4);

        recview=(RecyclerView)findViewById(R.id.rec4);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Insert2> options =
                new FirebaseRecyclerOptions.Builder<Insert2>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Admincategory"), Insert2.class)
                        .build();

        adapter=new Edit_adapter(options);
        recview.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    }
