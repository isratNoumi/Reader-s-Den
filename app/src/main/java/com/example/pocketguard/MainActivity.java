package com.example.pocketguard;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static int splash=3000;
    Animation animation;
    ImageView logo;
    TextView textview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animation= AnimationUtils.loadAnimation(this,R.anim.animation);
        logo=findViewById(R.id.logo);
        textview1=findViewById(R.id.textview1);
        logo.setAlpha(120);
        logo.setAnimation(animation);
        textview1.setAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
                finish();


            }
        },splash);
    }
}