package com.example.kindful;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserHome extends AppCompatActivity {
    private TextView user ,transactionBtn;
    private RecyclerView recyclerView;
    com.google.firebase.database.DatabaseReference database;
    MyAdapter1 myAdapter;
    ArrayList<model> list;
    UserData post;
    Button btnT;
    Bundle bundle=new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        user=findViewById(R.id.username);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        System.out.println(uid);
        transactionBtn=findViewById(R.id.transactionBtn);
        transactionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserHome.this,UserTransaction.class);
                startActivity(intent);
            }
        });
        try{
            userdataFetcher(uid);

        }
        catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        recyclerView = (RecyclerView) findViewById(R.id.list_item);
        database=FirebaseDatabase.getInstance().getReference("RecieverData");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myAdapter = new MyAdapter1(this,list);
        recyclerView.setAdapter(myAdapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    model user = dataSnapshot.getValue(model.class);
                    list.add(user);
                    System.out.println("----------------------------------");
                }
                myAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void userdataFetcher(String uid) {
        database=FirebaseDatabase.getInstance().getReference("DonorData");
        database.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                post = dataSnapshot.getValue(UserData.class);
                user.setText("Welcome, "+post.getUsername());
                bundle.putString("username",post.getUsername());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

}