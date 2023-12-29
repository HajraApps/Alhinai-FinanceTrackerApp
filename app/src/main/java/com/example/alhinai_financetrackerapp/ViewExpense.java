package com.example.alhinai_financetrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewExpense extends AppCompatActivity {
    TextView txt_Food, txt_Shopping, txt_Saving, txt_Charity;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expense);

        txt_Charity= findViewById(R.id.txt_Charity);
        txt_Food= findViewById(R.id.txt_Food);
        txt_Saving= findViewById(R.id.txt_Saving);
        txt_Shopping= findViewById(R.id.txt_Shopping);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        ref = FirebaseDatabase.getInstance().getReference("User").child(user.getUid()).child("Balance");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    UserAccount userAccount = dataSnapshot.getValue(UserAccount.class);
                    txt_Charity.setText(userAccount.getAccCharity() +" OMR");
                    txt_Saving.setText(userAccount.getAccSaving() +" OMR");
                    txt_Shopping.setText(userAccount.getAccShopping() +" OMR");
                    txt_Food.setText(userAccount.getAccFood() +" OMR");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ViewExpense.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ViewExpense.this, Home.class));
        finish();
    }
}