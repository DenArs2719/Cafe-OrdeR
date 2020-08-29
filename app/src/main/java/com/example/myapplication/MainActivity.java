package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private EditText editName;
    private EditText editPassword;
    private Button buttonConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editPassword = findViewById(R.id.editPassword);
        buttonConfirm = findViewById(R.id.buttonConfirm);
    }

    public void onClickCreateOrder(View view)
    {
        ///trim() - delete space
        String name = editName.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        if(name.length() == 0 || password.length() == 0)
        {
            ///create toast
            Toast.makeText(this,R.string.show_toast,Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(this,CreateOrderActivity.class);
            intent.putExtra("name",name);
            intent.putExtra("password",password);
            startActivity(intent);
        }

    }
}