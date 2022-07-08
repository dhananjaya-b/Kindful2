package com.example.kindful;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserTransaction extends AppCompatActivity {
    com.google.firebase.database.DatabaseReference database;
    MyAdapter1 myAdapter;
    ArrayList<TransactionModel> list;
    UserData post;
    TextView user;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_transaction);
        //Bundle bundle = getIntent().getExtras();
        //String username=bundle.getString("username");
        user=findViewById(R.id.usernamer);
        user.setText("Welcome"+" "+"dj");
        recyclerView = (RecyclerView) findViewById(R.id.transition_list);
        database= FirebaseDatabase.getInstance().getReference("TransactionData");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        UserTrasactionAdapter trasactionAdapter = new UserTrasactionAdapter(this,list);
        recyclerView.setAdapter(trasactionAdapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    TransactionModel user = dataSnapshot.getValue(TransactionModel.class);
                    list.add(user);
                }
                trasactionAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}