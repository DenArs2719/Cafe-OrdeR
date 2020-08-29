package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CreateOrderActivity extends AppCompatActivity
{
    private TextView textViewHello;
    private TextView textViewAdditions;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxLemon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;

    private String drink;
    private String name;
    private String password;

    private StringBuilder builderAdditions;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        Intent intent = getIntent();

        ///check if we have data in our intent
        if(intent.hasExtra("name") && intent.hasExtra("password"))
        {
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        }
        else
        {
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }

        drink = getString(R.string.tea);
        textViewHello = findViewById(R.id.textViewHello);
        String hello = String.format(getString(R.string.hello_user),name);
        textViewHello.setText(hello);
        textViewAdditions = findViewById(R.id.textViewAdditions);
        String additions = String.format(getString(R.string.additions),drink);
        textViewAdditions.setText(additions);

        checkBoxMilk = findViewById(R.id.checkBoxMilk);
        checkBoxSugar = findViewById(R.id.checkBoxSugar);
        checkBoxLemon = findViewById(R.id.checkBoxLemon);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);

        builderAdditions = new StringBuilder();

    }

    public void onClickChangeDrink(View view)
    {
        ///get button who pick user
        RadioButton button = (RadioButton) view;

        int id = button.getId();

        if(id == R.id.radioButtonTea)
        {
            drink = getString(R.string.tea);
            spinnerTea.setVisibility(View.VISIBLE);
            spinnerCoffee.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.VISIBLE);
        }
        else if(id == R.id.radioButtonCoffee)
        {
            drink = getString(R.string.coffee);
            spinnerCoffee.setVisibility(View.VISIBLE);
            spinnerTea.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.INVISIBLE);
        }

        String additions = String.format(getString(R.string.additions),drink);
        textViewAdditions.setText(additions);
    }

    public void onClickSendOrder(View view)
    {

        ///if builder has something ,clear this
        builderAdditions.setLength(0);

        if(checkBoxMilk.isChecked())
        {
            if(builderAdditions.length() != 0) {
                builderAdditions.append(",").append(getString(R.string.milk)).append(" ");
            }
            else
            {
                builderAdditions.append(getString(R.string.milk)).append(" ");
            }
        }
        if(checkBoxSugar.isChecked())
        {
            if(builderAdditions.length() != 0) {
                builderAdditions.append(",").append(getString(R.string.sugar)).append(" ");
            }
            else
            {
                builderAdditions.append(getString(R.string.sugar)).append(" ");
            }
        }
        if(checkBoxLemon.isChecked() && drink.equals(getString(R.string.tea)))
        {
            if(builderAdditions.length() != 0) {
                builderAdditions.append(",").append(getString(R.string.lemon)).append(" ");
            }
            else
            {
                builderAdditions.append(getString(R.string.lemon)).append(" ");
            }
        }

        String kindOfDrink = "";
        if(drink.equals(getString(R.string.tea)))
        {
            kindOfDrink =  spinnerTea.getSelectedItem().toString();
        }
        else
        {
            kindOfDrink =  spinnerCoffee.getSelectedItem().toString();
        }
        String order = String.format(getString(R.string.order),name,password,drink,kindOfDrink);

        String additions = "";
        if(builderAdditions.length() > 0)
        {
            additions = "\n"+getString(R.string.need_additions)+builderAdditions.toString();
        }
        else
        {
            additions = "";
        }

        String finalOrder = "";

        finalOrder = order + additions;

        Intent intent = new Intent(this,OrderDetailActivity.class);
        intent.putExtra("finalOrder",finalOrder);
        startActivity(intent);

    }
}