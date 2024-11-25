package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity  implements View.OnClickListener {
   private CardView card1,card2,card3,card4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        card1=(CardView) findViewById(R.id.crd1);
        card2=(CardView) findViewById(R.id.crd2);
        card3=(CardView) findViewById(R.id.crd3);
        card4=(CardView) findViewById(R.id.crd4);
        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent in;
        switch (v.getId()){
            case R.id.crd1:
                in=new Intent(this,AddbooksActivity.class);
                startActivity(in);
                break;
            case R.id.crd2:
                in=new Intent(this,Card2Activity.class);
                startActivity(in);
                break;
            case R.id.crd3:
                in=new Intent(this,Card4Activity.class);
                startActivity(in);
                break;
            case R.id.crd4:
                in=new Intent(this,Card3Activity2.class);
                startActivity(in);
                break;
        }

    }
}