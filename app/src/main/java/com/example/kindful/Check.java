package com.example.kindful;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Check extends AppCompatActivity {
    Button callSignUp;
    Button callSignUpFund;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_check);
        callSignUp=findViewById(R.id.sign_up);
        callSignUpFund=findViewById(R.id.sign_up_fundraise);

        callSignUp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent=new Intent(Check.this,SignUp.class);
                startActivity(intent);
            }

        });
        callSignUpFund.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent=new Intent(Check.this,SignUpFund.class);
                startActivity(intent);
            }

        });
    }
}