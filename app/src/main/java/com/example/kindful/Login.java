package com.example.kindful;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {
   Button callSignUp,login_btn;
   ImageView image;
   TextView logoText,sloganText;
   TextInputLayout username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);  //status bar hide
        setContentView(R.layout.activity_login);

        callSignUp=findViewById(R.id.signup_screen);

        callSignUp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent=new Intent(Login.this,Check.class);
                startActivity(intent);
            }

        });
    }
}