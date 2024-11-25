package com.example.pocketguard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class OrderActivity extends AppCompatActivity {
    RecyclerView recview4;
    Orderadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        recview4=(RecyclerView)findViewById(R.id.recycler3);
        recview4.setLayoutManager(new LinearLayoutManager(this));

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

        FirebaseRecyclerOptions<Order_Helper> options =
                new FirebaseRecyclerOptions.Builder<Order_Helper>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("OrderList").child(currentFirebaseUser.getUid()), Order_Helper.class)
                        .build();

        adapter=new Orderadapter(options);
        recview4.setAdapter(adapter);
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