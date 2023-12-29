package com.example.alhinai_financetrackerapp;

import androidx.appcompat.app.AppCompatActivity;

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

public class Calculator2 extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator2);

        b0= findViewById(R.id.b02);
        b1= findViewById(R.id.b12);
        b2= findViewById(R.id.b22);
        b3= findViewById(R.id.b32);
        b4= findViewById(R.id.b42);
        b5= findViewById(R.id.b52);
        b6= findViewById(R.id.b62);
        b7= findViewById(R.id.b72);
        b8= findViewById(R.id.b82);
        b9= findViewById(R.id.b92);
        bPlus= findViewById(R.id.bPlus2);
        bMinus= findViewById(R.id.bMinus2);
        c= findViewById(R.id.c2);
        b_update= findViewById(R.id.b_update2);
        show_cal= findViewById(R.id.cal_show2);
        expVal= findViewById(R.id.expVal2);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        ref = FirebaseDatabase.getInstance().getReference("User").child(user.getUid()).child("Balance");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    UserAccount userAccount = dataSnapshot.getValue(UserAccount.class);
                    expVal.setText("Current Shopping Expense is: " +userAccount.getAccShopping());
                    ss= userAccount.getAccShopping();
                    dataGet= userAccount.getAccShopping();
                    balance= userAccount.getAccBalance();
                    savingVal= userAccount.getAccSaving();
                    charityVal= userAccount.getAccCharity();
                    foodVal= userAccount.getAccFood();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Calculator2.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_cal.setText("");
                expVal.setText("Current Shopping Expense is: " +ss);

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
                expVal.setText("Current Shopping Expense is: " +saveVal);
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
                expVal.setText("Current Shopping Expense is: " +saveVal);
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
        UserAccount userAccount= new UserAccount(finalBalance, charityVal, savingVal, saveVal, foodVal);
        ref.setValue(userAccount);
        Toast.makeText(Calculator2.this, "Shopping Expense is Updated", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Calculator2.this, ShoppingCal.class));
        finish();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Calculator2.this, Home.class));
        finish();
    }
}