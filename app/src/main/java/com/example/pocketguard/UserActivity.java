package com.example.pocketguard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageButton mChooseImage;
    private Uri userImage;
    private CircleImageView mImageView;
    private EditText text1;
    private EditText text2;
    private EditText text3;
    private EditText text4;
    private TextView edittext;
    private Button mbuttonupload;


    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef,mref;
    private StorageTask mUploadTask;
    FirebaseAuth mauth;
    String img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mChooseImage = findViewById(R.id.imgv);
        text1 = findViewById(R.id.editTextTextPersonName);
        text2 = findViewById(R.id.editTextTextPersonName2);
        text3 = findViewById(R.id.editTextTextPersonName3);
        text4 = findViewById(R.id.editTextTextPersonName4);
        edittext=findViewById(R.id.txtedit);
        mImageView=findViewById(R.id.usimg);
        mbuttonupload = findViewById(R.id.btnup);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("User");
        mStorageRef = FirebaseStorage.getInstance().getReference();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        System.out.println(currentFirebaseUser);
        mref=mDatabaseRef.child(currentFirebaseUser.getUid());
        mref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot = task.getResult();
                if (task.isSuccessful()) {
                    if (dataSnapshot.exists()) {
                        User_helper user_helper = dataSnapshot.getValue(User_helper.class);
                        text1.setText(user_helper.getUserName());
                        text2.setText(user_helper.getEmail());
                        text3.setText(user_helper.getPhoneNo());
                        text4.setText(user_helper.getAddress());
                        Picasso.get().load(user_helper.getUserImage()).into(mImageView);
                        img = user_helper.getUserImage();
                    }

                }
            }
        });
    /*mref.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull  DataSnapshot snapshot) {
        User_helper user_helper=snapshot.getValue(User_helper.class);
        text1.setText(user_helper.getUserName());
        text2.setText(user_helper.getEmail());
        text3.setText(user_helper.getPhoneNo());
        text4.setText(user_helper.getAddress());
        Picasso.get().load(user_helper.getUserImage()).into(mImageView);
        img=user_helper.getUserImage();
        }

        @Override
        public void onCancelled(@NonNull  DatabaseError error) {
            System.out.println("Please enter your profile info");
        }
    });

     */

      edittext.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              mref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                  @Override
                  public void onComplete(@NonNull  Task<DataSnapshot> task) {
                      DataSnapshot dataSnapshot = task.getResult();
                      if (task.isSuccessful()) {
                          if (dataSnapshot.exists()) {
                              User_helper user_helper = dataSnapshot.getValue(User_helper.class);
                              Intent intent= new Intent(UserActivity.this ,EditActivity.class);

                              intent.putExtra("userName",user_helper.getUserName());
                              intent.putExtra("userImage",user_helper.getUserImage());
                              intent.putExtra("email",user_helper.getEmail());
                              intent.putExtra("address",user_helper.getAddress());
                              intent.putExtra("phoneNo",user_helper.getPhoneNo());
                              startActivity(intent);
                          }

                      }
                  }
              });



          }
      });



        mChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        mbuttonupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(UserActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {


                    uploadFile();
                }
            }
        });

    }

    private void uploadFile() {
        if (userImage!=null && text1.getText() != null&& text2.getText()!=null && text3.getText()!=null && text4.getText()!=null ) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(userImage));
            mUploadTask = fileReference.putFile(userImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                }
                            }, 500);
                            Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                            result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String photoStringLink = uri.toString();


                                    Toast.makeText(UserActivity.this, "Upload successful", Toast.LENGTH_LONG).show();

                                    User_helper upload = new User_helper(photoStringLink, text1.getText().toString().trim(), text2.getText().toString().trim(), text4.getText().toString().trim(), text3.getText().toString().trim());
                                    String uploadId = mref.push().getKey();
                                    mref.setValue(upload);
                                   mbuttonupload.setVisibility(View.GONE);


                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UserActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else{
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();

        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            userImage = data.getData();
            Picasso.get().load(userImage).into(mImageView);
        }
    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
}
