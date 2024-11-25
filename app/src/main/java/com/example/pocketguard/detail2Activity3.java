package com.example.pocketguard;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class detail2Activity3 extends AppCompatActivity {
    TextView quantity,description;
    int totalQuantity=1;
    int totalPrice=0;
    TextView bookn,bookpr,bookdes;
    ImageView img,addItem,removeItem;
    String tempname,tempimg, tempprc,tempcat,tempdes;
    private DatabaseReference mDatabaseRef,mref;

    Button addToCart;
    int price;
    Category2 category2=null;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail23);
        //final Object object=getIntent().getSerializableExtra("BookName");

        firestore=FirebaseFirestore.getInstance();
        auth =FirebaseAuth.getInstance();
        Intent intent=getIntent();
        tempname =intent.getStringExtra("BookName");
        tempimg=intent.getStringExtra("BookImage");
        tempprc=intent.getStringExtra("BookPrice");
        tempdes=intent.getStringExtra("description");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("User_cart");
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        mref=mDatabaseRef.child(currentFirebaseUser.getUid());


        price=Integer.parseInt(tempprc);



        description=findViewById(R.id.detailed_dec);
        addItem=findViewById(R.id.add_item);
        removeItem=findViewById(R.id.remove_item);
        quantity=findViewById(R.id.quantity);
        bookn=findViewById(R.id.bookName23);
        img=findViewById(R.id.imagePoster);
        bookpr=findViewById(R.id.bookPrice23);
        addToCart=findViewById(R.id.add_to_cart);
        bookdes=findViewById(R.id.detailed_dec);

        if(!tempname.isEmpty() && !tempimg.isEmpty()&& !tempprc.isEmpty())
        {
            bookn.setText(tempname);
            Picasso.get().load(tempimg).into(img);
            bookpr.setText("BDT " +tempprc);
            bookdes.setText(tempdes);
            totalPrice=Integer.parseInt(tempprc)*totalQuantity;

        }
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedToCart();
            }
        });


        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity<10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice=Integer.parseInt(tempprc) *totalQuantity;


                }
            }
        });
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity>1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice=Integer.parseInt(tempprc) *totalQuantity;

                }

            }
        });
    }

    private void addedToCart() {
        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate =Calendar.getInstance();

        SimpleDateFormat currentDate =new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate=currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime =new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentTime.format(calForDate.getTime());

        final HashMap<String, Object> cartMap =new HashMap<>();

         cartMap.put("BookName",bookn.getText().toString());
        cartMap.put("BookPrice",bookpr.getText().toString());
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalPrice",totalPrice);
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        MyCartModel upload = new MyCartModel(bookn.getText().toString(),bookpr.getText().toString(),saveCurrentDate,saveCurrentTime,quantity.getText().toString(),totalPrice,auth.getCurrentUser().getUid());
        mref=mDatabaseRef.child(currentFirebaseUser.getUid());
        mref.setValue(upload);
       // cartMap.put("UserName",auth.getCurrentUser().getDisplayName());

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(detail2Activity3.this,"Added To Your Cart",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}