package com.example.pocketguard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Log_In extends AppCompatActivity {
    Button login;
    EditText name, password;
    FirebaseAuth fireauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        TextView textView2 = findViewById(R.id.textView2);
        TextView forgotpass = findViewById(R.id.textView4);
        name = findViewById(R.id.editPersonName);
        password = findViewById(R.id.editPassword);

        login = findViewById(R.id.button);

        FirebaseApp.initializeApp(this);
        fireauth = FirebaseAuth.getInstance();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("User");

        String text = "Don't have a account?Register Here!";
        SpannableString ss = new SpannableString(text);

        ClickableSpan cl = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(Log_In.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }


        };
        ss.setSpan(cl, 21, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView2.setText(ss);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String remail = name.getText().toString().trim();
                String rpass = password.getText().toString().trim();


                if (TextUtils.isEmpty(remail)) {
                    name.setError("UserName is required");
                    return;
                }
                if (TextUtils.isEmpty(rpass)) {
                    password.setError("Password is required");
                    return;
                }
                if (rpass.length() < 7) {
                    password.setError("Password must be 7 characters long");
                    return;
                }
/*
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(  DataSnapshot snapshot) {

                        if (snapshot.child(name.getText().toString()).exists()) {
                            loginhelper user = snapshot.child(name.getText().toString()).getValue(loginhelper.class);
                            if (user.getPass().equals(password.getText().toString())) {
                                Toast.makeText(Log_In.this, "Logged In Successfully!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));

                                name.setText("");
                            } else {
                                Toast.makeText(Log_In.this, "Error!", Toast.LENGTH_SHORT).show();

                            }
                        }
                        else{
                            Toast.makeText(Log_In.this, "User doesn't exist!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(  DatabaseError error) {
                    }


                });
*/
                fireauth.signInWithEmailAndPassword(remail, rpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Log_In.this, "Logged In Successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));

                            name.setText("");
                            password.setText("");
                        } else {
                            Toast.makeText(Log_In.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                        }
                    }
                });


            }
        });




        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetmail = new EditText(v.getContext());
                AlertDialog.Builder passwordresetDialog = new AlertDialog.Builder(v.getContext());
                passwordresetDialog.setTitle("Reset Password?");
                passwordresetDialog.setMessage("Enter your email to receive reset link");
                passwordresetDialog.setView(resetmail);
                passwordresetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail = resetmail.getText().toString();
                        fireauth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Log_In.this, "Reset link has been sent to your email!", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Log_In.this, "Error!Reset link is not sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passwordresetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                passwordresetDialog.create().show();
            }
        });



    }
}
