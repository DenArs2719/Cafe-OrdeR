package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity
{
    private TextView textViewGetOrder;
    private TextView textViewName;
    private TextView textViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        textViewName = findViewById(R.id.textViewName);
        textViewPassword = findViewById(R.id.textViewPassword);
        textViewGetOrder = findViewById(R.id.textViewGetOrder);

        Intent intent = getIntent();
        if (intent.hasExtra("finalOrder"))
        {

            String userOrder = intent.getStringExtra("finalOrder");

            String[] str = userOrder.split("\\s");

            String userName = "";
            String userPassword = "";

            for(int i=0;i<str.length;i++)
            {
                userName = str[i];
                userPassword = str[i + 1];
                break;
            }

            textViewName.setText(userName);
            textViewPassword.setText(userPassword);
            textViewGetOrder.setText(userOrder);

        } else
            {
            Intent backToLogin = new Intent(this, MainActivity.class);
            startActivity(backToLogin);
        }

    }
}