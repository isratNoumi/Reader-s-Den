package com.example.pocketguard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CartActivity extends AppCompatActivity {
    Toolbar toolbar;
    private FirebaseFirestore firestore;
    TextView bookn,totalQuantity,totalPrice;
    private FirebaseAuth auth;
    // ProgressBar progressBar;

    Button buyNow;
    TextView overTotalAmount;

    private RecyclerView recyclerView;
    private MyCartAdapter cartAdapter;
    //MyCartModel cartModel;
    private List<MyCartModel> cartModelList;
    List<Model> modelList;
    MyAdapter3 myAdapter;
    private PaymentActivity paymentActivity;


    public CartActivity(){

    }

    @Override
    protected  void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        toolbar= findViewById(R.id.my_cart_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //get data from my cart adapter
       /* LocalBroadcastManager.getInstance(this)
                .registerReceiver(mMessageReceiver,new IntentFilter("MyTotalAmount"));
*/

        bookn= findViewById(R.id.bookName_txt);
        totalPrice= findViewById(R.id.totalPrice_txt);
        totalQuantity= findViewById(R.id.totalQuantity_txt);




        buyNow=findViewById(R.id.buy_now);
        recyclerView = findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));





        //  modelList = new ArrayList<>();
        // myAdapter = new MyAdapter(paymentActivity,modelList);
        //  recyclerView.setAdapter(myAdapter);


        cartModelList = new ArrayList<>();
        cartAdapter = new MyCartAdapter(this,cartModelList);
        recyclerView.setAdapter(cartAdapter);
        //progressBar = ProgressBar();


        ItemTouchHelper touchHelper = new ItemTouchHelper(new TouchHelper(cartAdapter));
        touchHelper.attachToRecyclerView(recyclerView);


        showBook();
        // cartModel = new MyCartModel();



        //*******************Buy NOW!!******************************
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Intent intent = new Intent(CartActivity.this,PaymentActivity.class);
                //  intent.putExtra("itemList", (Serializable) modelList);
                // startActivity(intent);
                // if(newPro)
                //startActivity(intent);
                startActivity(new Intent(CartActivity.this,PaymentActivity.class));
            }
        });



    }

   /* public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_cart,container,false);

        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        recyclerView=root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        overTotalAmount = root.findViewById(R.id.textView6);

        LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(mMessageReceiver,new IntentFilter("MyTotalAmount"));


        cartModelList=new ArrayList<>();
        cartAdapter=new MyCartAdapter(getActivity(),cartModelList);
        recyclerView.setAdapter(cartAdapter);

        db.collection("AddToCart").document(auth.getCurrentUser().getEmail())
                .collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                        MyCartModel cartModel= documentSnapshot.toObject(MyCartModel.class);
                        cartModelList.add(cartModel);
                        cartAdapter.notifyDataSetChanged();
                    }
                }

            }
        });

        return root;

    }*/


    public void showBook() {

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        MyCartModel cartModel = documentSnapshot.toObject(MyCartModel.class);
                        //cartAdapter.notifyRemoved();
                        String documentId = documentSnapshot.getId();

                        cartModel.setDocumentId(documentId);

                        //  MyCartModel myCartModel = documentSnapshot.toObject(MyCartModel.class);
                        cartModelList.add(cartModel);
                        cartAdapter.notifyDataSetChanged();
                        calculateTotalAmount(cartModelList);
                        recyclerView.setVisibility(View.VISIBLE);

                    }


                }
            }


        });

            /*
        FirebaseRecyclerOptions<MyCartModel> options =
                new FirebaseRecyclerOptions.Builder<MyCartModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User_cart"), MyCartModel.class)
                        .build();

        cartAdapter=new MyCartAdapter(options);
        recyclerView.setAdapter(cartAdapter);



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
*/
}

    private void calculateTotalAmount(List<MyCartModel> cartModelList) {
        double totalAmount =0.0;
        for(MyCartModel myCartModel : cartModelList){
            totalAmount+=myCartModel.getTotalPrice();
        }
       // overTotalAmount.setText("Total "+totalAmount+ " TK");
    }
    /*public void notifyRemoved() {
        cartModelList.remove(position);
        //notifyItemRemoved(position);
        showBook();
    }*/

     /*   public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int totalBill = intent.getIntExtra("totalAmount",0);
            overTotalAmount.setText("Total Bill:"+ totalBill+ " TK");
        }
    };*/

}