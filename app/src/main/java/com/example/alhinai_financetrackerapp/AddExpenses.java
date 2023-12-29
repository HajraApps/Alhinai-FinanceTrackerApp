package com.example.alhinai_financetrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddExpenses extends AppCompatActivity {
    Button charity, saving, shopping, food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);

        charity= findViewById(R.id.charity1);
        saving= findViewById(R.id.saving1);
        shopping= findViewById(R.id.shopping1);
        food= findViewById(R.id.food1);

        charity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddExpenses.this, CharityCal.class));
                finish();
            }
        });

        saving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddExpenses.this, SavingsCal.class));
                finish();
            }
        });

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddExpenses.this, ShoppingCal.class));
                finish();
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddExpenses.this, FoodCal.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddExpenses.this, Home.class));
        finish();
    }
}