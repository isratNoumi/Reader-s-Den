package com.example.pocketguard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    EditText name,email,phone,password;
    Button register;
    FirebaseAuth fireauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        TextView textView3 = findViewById(R.id.textView3);
        name=findViewById(R.id.editPerson);
        email=findViewById(R.id.editemail1);
        phone=findViewById(R.id.editphone1);
        password=findViewById(R.id.editPassword1);
        register=findViewById(R.id.button1);
        FirebaseApp.initializeApp(this);
        fireauth=FirebaseAuth.getInstance();

        String text = "Already Registered?Log In Here!";
        SpannableString ss1 = new SpannableString(text);
        ClickableSpan cl = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent=new Intent(RegistrationActivity.this,Log_In.class);
                startActivity(intent);
                finish();
            }
        };
        ss1.setSpan(cl,19,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView3.setText(ss1);
        textView3.setMovementMethod(LinkMovementMethod.getInstance());

       if(fireauth.getCurrentUser()!=null)
       {
           startActivity(new Intent(getApplicationContext(),MainActivity.class));
           finish();
       }





        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rname=name.getText().toString().trim();
                String remail=email.getText().toString().trim();
                String rpass=password.getText().toString().trim();
                String rphone=phone.getText().toString().trim();

                if(TextUtils.isEmpty(remail))
                {
                    email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(rname))
                {
                    name.setError("UserName is required");
                    return;
                }
                if(TextUtils.isEmpty(rpass))
                {
                    password.setError("Password is required");
                    return;
                }
                if(rpass.length()<7)
                {
                    password.setError("Password must be 7 characters long");
                    return;
                }
                if(TextUtils.isEmpty(rphone))
                {
                    phone.setError("Phone No is required");
                    return;
                }




                fireauth.createUserWithEmailAndPassword(remail,rpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Log_In.class));
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
    }
}