package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity
{
    private TextView textViewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        textViewOrder = findViewById(R.id.textViewOrder);
        Intent intent = getIntent();
        if(intent.hasExtra("finalOrder"))
        {
             String order = intent.getStringExtra("finalOrder");
             textViewOrder.setText(order);
        }
        else
        {
            Intent backToLogin = new Intent(this,MainActivity.class);
            startActivity(backToLogin);
        }

    }
}