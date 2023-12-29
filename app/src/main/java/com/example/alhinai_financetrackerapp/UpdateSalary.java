package com.example.alhinai_financetrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateSalary extends AppCompatActivity {

    EditText balance;
    Button up_balance;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference ref;
    String user_balance;
    String charityVal, savingVal, shoppingVal, foodVal;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_salary);

        balance= findViewById(R.id.up_bal);
        up_balance= findViewById(R.id.save_up_balance);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        ref = FirebaseDatabase.getInstance().getReference("User").child(user.getUid()).child("Balance");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    UserAccount userAccount = dataSnapshot.getValue(UserAccount.class);
                    balance.setText(userAccount.getAccBalance());
                    charityVal= userAccount.getAccCharity();
                    savingVal= userAccount.getAccSaving();
                    shoppingVal= userAccount.getAccShopping();
                    foodVal= userAccount.getAccFood();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UpdateSalary.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });


        up_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    UserAccount userAccount= new UserAccount(user_balance, charityVal, savingVal, shoppingVal, foodVal);
                    ref.setValue(userAccount);
                    Toast.makeText(UpdateSalary.this, "Salary is Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateSalary.this, AddSalary.class));
                    finish();

                }

            }
        });
    }

    private Boolean validate(){
        boolean result= false;

        user_balance = balance.getText().toString();

        if(user_balance.isEmpty()){
            Toast.makeText(this, "Please enter any salary amount", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(UpdateSalary.this, AddSalary.class));
        finish();
    }
}