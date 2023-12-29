package com.example.alhinai_financetrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExpensesCalculations extends AppCompatActivity {
    Button charity, saving, shopping, food;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_calculations);

        charity= findViewById(R.id.charity);
        saving= findViewById(R.id.saving);
        shopping= findViewById(R.id.shopping);
        food= findViewById(R.id.food);

        charity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpensesCalculations.this, Calculator.class));
                finish();
            }
        });

        saving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpensesCalculations.this, Calculator1.class));
                finish();
            }
        });

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpensesCalculations.this, Calculator2.class));
                finish();
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpensesCalculations.this, Calculator3.class));
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ExpensesCalculations.this, Home.class));
    }

}