package com.example.kindful;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Donate extends AppCompatActivity {
    TextView Fullname,Details,Amount,payBtn;
    TextInputLayout text;
    EditText text1;
    String senderId,fullname;
    String recieverId;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    com.google.firebase.database.DatabaseReference database;
    UserData post;
    private int amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        Bundle bundle = getIntent().getExtras();
        fullname=bundle.getString("name");
        String details=bundle.getString("details");
        String amount=bundle.getString("amount");
        String phoneNumber=bundle.getString("phoneNumber");
        Fullname=findViewById(R.id.fullname);
        Details=findViewById(R.id.deatils);
        Amount=findViewById(R.id.amount);
        Fullname.setText(fullname);
        Details.setText(details);
        Amount.setText(amount);
        payBtn=findViewById(R.id.paybtn);
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recieverId=bundle.getString("recieverId");
                text=findViewById(R.id.amountPay);
                text1=text.getEditText();
                String amountToPay=text1.getText().toString();
                senderId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("TransactionData");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String formattedDate = df.format(c);
                String date=c.toString();
                String amt=String.valueOf(amount);
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                Toast.makeText(Donate.this, "checking credentials",Toast.LENGTH_SHORT).show();
                TransactionModel transactionModel=new TransactionModel(senderId,recieverId,date.substring(0,20),amountToPay,"Sender"+uid.substring(0,5),fullname);
                String tid=getSaltString();
                databaseReference.child(tid).setValue(transactionModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Donate.this, "successrjut", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(Donate.this,UserTransaction.class);
                        startActivity(i);
                    }
                });



            }
        });

    }
    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}