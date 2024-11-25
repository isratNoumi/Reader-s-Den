package com.example.pocketguard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditActivity extends AppCompatActivity {
     String name,email2,phone,image,address2;
    private CircleImageView mImageView;
    private EditText text1;
    private EditText text2;
    private EditText text3;
    private EditText text4;
    private Button mbuttonupdate;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef,mref;
    private StorageTask mUploadTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent=getIntent();
        name =intent.getStringExtra("userName");
        image=intent.getStringExtra("userImage");
        email2=intent.getStringExtra("email");
        phone=intent.getStringExtra("phoneNo");
        address2=intent.getStringExtra("address");
        text1 = findViewById(R.id.editTextTextPersonName);
        text2 = findViewById(R.id.editTextTextPersonName2);
        text3 = findViewById(R.id.editTextTextPersonName3);
        text4=findViewById(R.id.editTextTextPersonName4);
        mImageView=findViewById(R.id.usimg);
        mbuttonupdate = findViewById(R.id.btnupdate);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("User");
        mStorageRef = FirebaseStorage.getInstance().getReference();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        mref=mDatabaseRef.child(currentFirebaseUser.getUid());

        if(!name.isEmpty() && !image.isEmpty() && !address2.isEmpty() && !email2.isEmpty() && !phone.isEmpty())
        {
            text1.setText(name);
           Picasso.get().load(image).into(mImageView);
            text3.setText(phone);
            text2.setText(email2);
           text4.setText(address2);

        }
        mbuttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isnameChanged()|| isaddressChanged()||isphoneChanged()|| isemailChanged()){
                    Toast.makeText(EditActivity.this, "Data has been updated!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditActivity.this, "Data is same and cannot be  updated!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

   /* private boolean isPhotoChanged() {
        if(!image.equals(text1.getText().toString()))
        {
            mref.child("userName").setValue(text1.getText().toString());
            return true;
        }
        else {
            return false;
        }
    }

    */

    private boolean isemailChanged() {
        if(!email2.equals(text2.getText().toString()))
        {
            mref.child("email").setValue(text2.getText().toString());
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isphoneChanged() {
        if(!phone.equals(text3.getText().toString()))
        {
            mref.child("phoneNo").setValue(text3.getText().toString());
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isaddressChanged() {
        if(!address2.equals(text4.getText().toString()))
        {
            mref.child("address").setValue(text4.getText().toString());
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isnameChanged() {
        if(!name.equals(text1.getText().toString()))
        {
            mref.child("userName").setValue(text1.getText().toString());
            return true;
        }
        else {
            return false;
        }
    }

}