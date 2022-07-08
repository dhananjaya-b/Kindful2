package com.example.kindful;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecieverHome extends AppCompatActivity {
    TextView fullname,details,amount,rcvdamount;
    private TextView user;
    com.google.firebase.database.DatabaseReference database;
    RecieverDetails post;
    ArrayList<RecieverDetails> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciever_home);
        fullname=findViewById(R.id.fullname);
        details=findViewById(R.id.deatils);
        amount=findViewById(R.id.amount);
        rcvdamount=findViewById(R.id.amountRecieved);
        user=findViewById(R.id.usernamer);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        System.out.println(uid);
        try{
            userdataFetcher(uid);
        }
        catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    private void userdataFetcher(String uid) {
        database= FirebaseDatabase.getInstance().getReference("RecieverData");

        database.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                post = dataSnapshot.getValue(RecieverDetails.class);
                user.setText("Welcome, "+post.getUsername());
                fullname.setText(post.getFullname());
                details.setText(post.getDetails());
                amount.setText(post.getAmount());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}