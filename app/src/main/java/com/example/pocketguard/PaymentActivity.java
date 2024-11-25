package com.example.pocketguard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class PaymentActivity extends AppCompatActivity {
    private Toolbar toolbar1;
    // private LinearLayout linearLayout;
    private RecyclerView recyclerView;
    //  private RecyclerView review;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    private MyAdapter3 adapter;
    private List<Model> modelList;
    TextView address,usname;
    CircleImageView uimg;
    Button btnc;
    private List<MyCartModel> list;
    private DatabaseReference mDatabaseRef,mref,mDatabaseRef2,mref2;
    String saveCurrentDate,saveCurrentTime;
    private FirebaseFirestore firestore= FirebaseFirestore.getInstance();

    private TextView overTotalAmount1,overTotalAmount2;

    //  private List<AddressModel> addressModelList;
    //  private AddressAdapter.SelectedAddress selectedAddress;
    // private AddressAdapter addressAdapter;
    public PaymentActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment2);

        // review = findViewById(R.id.r2_view);
        //review.setHasFixedSize(true);
        //review.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerView = findViewById(R.id.r_view);
        usname=findViewById(R.id.name_er);
        address=findViewById(R.id.add_er);
        uimg=findViewById(R.id.imgo);
        btnc=findViewById(R.id.confirmbtn);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();



        toolbar1= findViewById(R.id.my_payment_toolbar);
       // setSupportActionBar(toolbar1);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mMessageReceiver1,new IntentFilter("MyTotalAmount"));

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mMessageReceiver,new IntentFilter("MyTotalAmount"));


        modelList = new ArrayList<>();
        adapter = new MyAdapter3(this ,modelList);
        recyclerView.setAdapter(adapter);

        Calendar calForDate =Calendar.getInstance();

        SimpleDateFormat currentDate =new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate=currentDate.format(calForDate.getTime());

        overTotalAmount1 = findViewById(R.id.textView69);
        overTotalAmount2=findViewById(R.id.textView78);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("User");
        mDatabaseRef2 = FirebaseDatabase.getInstance().getReference("OrderList");
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        mref=mDatabaseRef.child(currentFirebaseUser.getUid());
        FirebaseUser currentFirebaseUser1 = FirebaseAuth.getInstance().getCurrentUser() ;
        mref2=mDatabaseRef2.child(currentFirebaseUser1.getUid());
        mref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot = task.getResult();
                if (task.isSuccessful()) {
                    if (dataSnapshot.exists()) {
                        User_helper user_helper = dataSnapshot.getValue(User_helper.class);
                        usname.setText(user_helper.getUserName());
                        address.setText(user_helper.getAddress());
                        Picasso.get().load(user_helper.getUserImage()).into(uimg);

                    }

                }
            }
        });

        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentActivity.this,"Your Order has been placed",Toast.LENGTH_SHORT).show();
                Order_Helper upload = new Order_Helper(currentFirebaseUser1.getUid(), overTotalAmount1.getText().toString().trim(), address.getText().toString().trim(),saveCurrentDate.trim());
                String uploadId = mref2.push().getKey();
                mref2.child(uploadId).setValue(upload);


                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("AddToCart")
                       .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull  Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot snapshot:task.getResult())
                        {
                            firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid()).collection("AddToCart").document(snapshot.getId()).delete();
                        }
                    }
                });





                startActivity(new Intent(PaymentActivity.this,DashboardActivity.class));

            }

        });
      /*  addressModelList = new ArrayList<>();
        addressAdapter = new AddressAdapter(getApplicationContext(),addressModelList,selectedAddress);
        review.setAdapter(addressAdapter); */

        //(addressAdapter));

        // List<AddressModel> list = (ArrayList<AddressModel>) getIntent().getSerializableExtra("itemsList");
        // if(list != null && list.size() > 0){
           /* for(AddressModel amodel : list){
                final HashMap<String, Object> addressMap =new HashMap<>();
                Map<String ,String> map =new HashMap<>();
                addressMap.put("userName",amodel.getUserName());
                addressMap.put("userCity",amodel.getUserCity());
                addressMap.put("userAddress",amodel.getUserAddress());
                addressMap.put("userCode",amodel.getUserCode());
                addressMap.put("userNumber",amodel.getUserNumber());
                */

              /*  db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("Address").get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                                addressModelList.clear();
                                for (DocumentSnapshot snapshot : task.getResult().getDocuments()){
                                    AddressModel addressModel = snapshot.toObject(AddressModel.class);
                                    addressModelList.add(addressModel);
                                }
                                addressAdapter.notifyDataSetChanged();
                            }
                        }); */
        //List<MyCartModel> list = (ArrayList<MyCartModel>) getIntent()
      /*  List<Model> list = (ArrayList<Model>)getIntent().getSerializableExtra("itemList");
        if(list != null && list.size() > 0){
            for(Model model : list){

                final HashMap<String, Object> cartMap =new HashMap<>();
                Map<String ,String> map =new HashMap<>();

                cartMap.put("BookName",model.getBookName());
                // cartMap.put("BookPrice",model.getBookPrice());
                //cartMap.put("currentDate",model.getCurrentDate());
                //cartMap.put("currentTime",model.getCurrentTime());
                cartMap.put("totalQuantity",model.getTotalQuantity());
                cartMap.put("totalPrice",model.getTotalPrice());


               db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                Model Model = documentSnapshot.toObject(Model.class);

                                String documentId = documentSnapshot.getId();

                                //cartModel.setDocumentId(documentId);

                                //  MyCartModel myCartModel = documentSnapshot.toObject(MyCartModel.class);
                                list.add(model);
                                adapter.notifyDataSetChanged();
                                recyclerView.setVisibility(View.VISIBLE);

                            }
                        }

                      //  Toast.makeText(PaymentActivity.this,"Your Order list is Ready",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });


            }

        }*/

        showData();
    }

    private void showData(){
        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        modelList.clear();
                        for(DocumentSnapshot snapshot : task.getResult().getDocuments()){
                            Model model = snapshot.toObject(Model.class);
                            modelList.add(model);
                            adapter.notifyDataSetChanged();

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(PaymentActivity.this,"Something went wrong!!!!!",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public BroadcastReceiver mMessageReceiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int crg=100;
            int totalBill1 = intent.getIntExtra("totalAmount",0);
            overTotalAmount1.setText("Total Bill:"+ (totalBill1+crg) + " TK");
        }
    };

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // int crg=100;
            int totalBill1 = intent.getIntExtra("totalAmount",0);
            overTotalAmount2.setText("Total Bill:"+ (totalBill1) + " TK");
        }
    };


}