package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {
    Button login;
    EditText name, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = findViewById(R.id.editPersonName);
        password = findViewById(R.id.editPassword);
        login = findViewById(R.id.button);
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
                if(remail.equals("190104070@aust.edu")&&rpass.equals("1357913")){
                    Toast.makeText(loginActivity.this, "Logged In Successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));

                    name.setText("");
                    password.setText("");
                } else {
                    Toast.makeText(loginActivity.this, "Error!" , Toast.LENGTH_SHORT).show();


                }

    }
});
    }
}