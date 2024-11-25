package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddbooksActivity extends AppCompatActivity {
   private static final int PICK_IMAGE_REQUEST=1;
    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private Uri BookImage;
    private String ctgory;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private EditText text1;
    private Spinner text2;
    private EditText text3;
    private EditText text4;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef,mref2,mref,mDatabaseRef2;
    private StorageTask mUploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbooks);
        mButtonChooseImage = findViewById(R.id.btn);
        mButtonUpload = findViewById(R.id.btn2);
        mImageView = findViewById(R.id.imgc);
        text1=findViewById(R.id.txt1);
        text2=findViewById(R.id.txt2);
        text3=findViewById(R.id.txt3);
        text4=findViewById(R.id.des);
       // text4=findViewById(R.id.txt4);
        String items[]=new String[]{"Select Book Category","Best Sellers","Novels","Science Fiction","Comics","History"};
        text2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,items));
        text2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             ctgory=text2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("category");
        mDatabaseRef2= FirebaseDatabase.getInstance().getReference("Admincategory");
        String ud=mDatabaseRef.push().getKey();
        mref=mDatabaseRef.child(ud);



        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(AddbooksActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {



                    uploadFile();
                }
            }
        });

    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {


            mref2=mDatabaseRef.child(ctgory).child("data");

        if ( BookImage != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(BookImage));
            mUploadTask = fileReference.putFile(BookImage)
                   .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                       @Override
                       public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                           Handler handler=new Handler();
                           handler.postDelayed(new Runnable() {
                               @Override
                               public void run() {
                               }
                           },500);
                           Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                           result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                               @Override
                               public void onSuccess(Uri uri) {
                                   String photoStringLink = uri.toString();


                                   Toast.makeText(AddbooksActivity.this, "Upload successful", Toast.LENGTH_LONG).show();

                                   Insert2 upload = new Insert2(photoStringLink, text1.getText().toString().toUpperCase().trim(), text3.getText().toString().trim(), ctgory.trim(),text4.getText().toString().trim());
                                   String uploadId = mref2.push().getKey();
                                   mref2.child(uploadId).setValue(upload);
                                   mDatabaseRef2.child(uploadId).setValue(upload);

                               }
                               });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddbooksActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
                     else{
                Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();

        }
    }


    @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                    && data != null && data.getData() != null) {
                BookImage = data.getData();
                Picasso.get().load(BookImage).into(mImageView);
            }
    }


    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    }