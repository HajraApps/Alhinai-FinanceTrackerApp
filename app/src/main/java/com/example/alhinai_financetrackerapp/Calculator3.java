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

public class Calculator3 extends AppCompatActivity {

    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9,bPlus, bMinus, c, b_update;
    EditText show_cal;
    TextView expVal;
    String valGet, dataGet;
    int val_Gett, dataGett;
    int val ;
    String saveVal;
    int ss1;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference ref;
    String user_balance;
    String charityVal, savingVal, shoppingVal, foodVal, balance;
    int int_bal;
    int cal_bal;
    String finalBalance;
    String ss;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator3);

        b0= findViewById(R.id.b03);
        b1= findViewById(R.id.b13);
        b2= findViewById(R.id.b23);
        b3= findViewById(R.id.b33);
        b4= findViewById(R.id.b43);
        b5= findViewById(R.id.b53);
        b6= findViewById(R.id.b63);
        b7= findViewById(R.id.b73);
        b8= findViewById(R.id.b83);
        b9= findViewById(R.id.b93);
        bPlus= findViewById(R.id.bPlus3);
        bMinus= findViewById(R.id.bMinus3);
        c= findViewById(R.id.c3);
        b_update= findViewById(R.id.b_update3);
        show_cal= findViewById(R.id.cal_show3);
        expVal= findViewById(R.id.expVal3);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        ref = FirebaseDatabase.getInstance().getReference("User").child(user.getUid()).child("Balance");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    UserAccount userAccount = dataSnapshot.getValue(UserAccount.class);
                    expVal.setText("Current Food Expense is: " +userAccount.getAccFood());
                    ss= userAccount.getAccFood();
                    dataGet= userAccount.getAccFood();
                    balance= userAccount.getAccBalance();
                    savingVal= userAccount.getAccSaving();
                    shoppingVal= userAccount.getAccShopping();
                    charityVal= userAccount.getAccCharity();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Calculator3.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_cal.setText("");
                expVal.setText("Current Food Expense is: " +ss);

            }
        });

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_cal.setText(show_cal.getText()+ "0");
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_cal.setText(show_cal.getText()+ "1");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_cal.setText(show_cal.getText()+ "2");
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_cal.setText(show_cal.getText()+ "3");
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_cal.setText(show_cal.getText()+ "4");
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_cal.setText(show_cal.getText()+ "5");
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_cal.setText(show_cal.getText()+ "6");
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_cal.setText(show_cal.getText()+ "7");
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_cal.setText(show_cal.getText()+ "8");
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_cal.setText(show_cal.getText()+ "9");
            }
        });

        bPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataGett= Integer.parseInt(dataGet);
                valGet= show_cal.getText().toString();
                val_Gett= Integer.parseInt(valGet);
                val= dataGett+val_Gett;

                saveVal= Integer.toString(val);

                show_cal.setText(saveVal);
                expVal.setText("Current Food Expense is: " +saveVal);
                saveCalculations();

            }
        });

        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataGett= Integer.parseInt(dataGet);
                valGet= show_cal.getText().toString();
                val_Gett= Integer.parseInt(valGet);
                val= dataGett-val_Gett;

                saveVal= Integer.toString(val);

                show_cal.setText(saveVal);
                expVal.setText("Current Food Expense is: " +saveVal);
                saveCalculations1();
            }
        });

        b_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalSave();
            }
        });


    }

    public void saveCalculations(){
        int_bal= Integer.parseInt(balance);
        if (int_bal>= val){
            cal_bal= int_bal-val;
            finalBalance= Integer.toString(cal_bal);
        }
        if (int_bal<val){
            Toast.makeText(this, "You have not enough balance", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveCalculations1(){
        int_bal= Integer.parseInt(balance);
        if (int_bal>= val){
            cal_bal= int_bal+val;
            finalBalance= Integer.toString(cal_bal);

        }
        if (int_bal<val){
            Toast.makeText(this, "Please enter less amount then current", Toast.LENGTH_SHORT).show();
        }
    }


    public void finalSave(){
        UserAccount userAccount= new UserAccount(finalBalance, charityVal, savingVal, shoppingVal, saveVal);
        ref.setValue(userAccount);
        Toast.makeText(Calculator3.this, "Food and Drink Expense is Updated", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Calculator3.this, CharityCal.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Calculator3.this, Home.class));
        finish();
    }
}