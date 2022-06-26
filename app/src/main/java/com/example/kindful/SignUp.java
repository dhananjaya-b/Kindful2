package com.example.kindful;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class SignUp extends AppCompatActivity {
    Button signlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        signlogin=findViewById(R.id.signlogin_button);

        signlogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent=new Intent(SignUp.this,Login.class);
                startActivity(intent);
                finish();
            }

        });
    }
}