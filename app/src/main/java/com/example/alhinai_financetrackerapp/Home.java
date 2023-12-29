package com.example.alhinai_financetrackerapp;

import static com.example.alhinai_financetrackerapp.Login.SHARED_PREFS;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {
    Button add_sal, cal_exp, add_exp, view_exp, logout;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference ref;
    String charityVal= "0", savingVal= "0", shoppingVal="0", foodVal="0", balance="0";

    public static final String SHARED_PREFS = "sharedPrefs";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        add_sal= findViewById(R.id.add_sal);
        cal_exp= findViewById(R.id.cal_exp);
        add_exp= findViewById(R.id.add_exp);
        view_exp= findViewById(R.id.view_exp);
        logout= findViewById(R.id.logout);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        ref = FirebaseDatabase.getInstance().getReference("User").child(user.getUid()).child("Balance");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Toast.makeText(Home.this, "", Toast.LENGTH_SHORT).show();
                }
                if (!dataSnapshot.exists()){
                    UserAccount userAccount= new UserAccount(balance, charityVal, savingVal, shoppingVal, foodVal);
                    ref.setValue(userAccount);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Home.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        add_sal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, AddSalary.class));
            }
        });

        cal_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, ExpensesCalculations.class));
            }
        });

        add_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, AddExpenses.class));
            }
        });

        view_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, ViewExpense.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                firebaseAuth.signOut();
                finish();
                Toast.makeText(Home.this, "Account Logout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Home.this, Login.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
       finish();
    }
}