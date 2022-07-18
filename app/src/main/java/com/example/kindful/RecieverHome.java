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
    Integer total=0;
    com.google.firebase.database.DatabaseReference database;
    RecieverDetails post;
    ArrayList<TransactionModel> list;
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
            cal();
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
    public void cal(){
        database= FirebaseDatabase.getInstance().getReference("TransactionData");
        list = new ArrayList<TransactionModel>();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TransactionModel user = dataSnapshot.getValue(TransactionModel.class);
                    if(user.getRecieverId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        total+=Integer.parseInt(user.getAmount());
                        list.add(user);
                    }
                }
                rcvdamount.setText(total.toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}