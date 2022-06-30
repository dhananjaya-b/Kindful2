package com.example.kindful;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Donate extends AppCompatActivity {
    TextView Fullname,Details,Amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        Bundle bundle = getIntent().getExtras();
        String fullname=bundle.getString("name");
        String details=bundle.getString("details");
        String amount=bundle.getString("amount");
        Fullname=findViewById(R.id.fullname);
        Details=findViewById(R.id.deatils);
        Amount=findViewById(R.id.amount);
        Fullname.setText(fullname);
        Details.setText(details);
        Amount.setText(amount);
    }
}