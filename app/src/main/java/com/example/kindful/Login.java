package com.example.kindful;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
     Button callSignUp,login_btn;
   ImageView image;
   TextView logoText,sloganText;
   TextInputLayout username,password;
   EditText useranme1,password1;
   FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);  //status bar hide
        setContentView(R.layout.activity_login);

          callSignUp=findViewById(R.id.signup_screen);
        login_btn=(Button) findViewById(R.id.signinBtn);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        useranme1=username.getEditText();
        password1=password.getEditText();
        mAuth=FirebaseAuth.getInstance();
        callSignUp.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                Intent intent=new Intent(Login.this,Check.class);
                startActivity(intent);
            }

        });
        login_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname=useranme1.getText().toString();
                String pwd=password1.getText().toString();
                if (TextUtils.isEmpty(uname) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(Login.this, "Please enter your credentials..", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(uname,pwd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithCustomToken:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);
                                    Toast.makeText(Login.this, "Successful",
                                            Toast.LENGTH_SHORT).show();
                                    redirect();
                                    Intent intent=new Intent(Login.this, UserHome.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithCustomToken:failure", task.getException());
                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                            }
                        });


            }
        });
    }
    public void redirect(){ ///checking is Donor or not
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("DonorData").child(uid);
        if(dbRef.child("isDonor").equals("true")){
            System.out.println("yessssssssssssssssssssssss");
        }
        else {
            System.out.println("noooooooooooooooooooooo");
        }
    }
}