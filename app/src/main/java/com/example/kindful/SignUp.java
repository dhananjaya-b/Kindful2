package com.example.kindful;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

public class SignUp extends AppCompatActivity {
    Button signlogin,signBtn;
    TextInputLayout fullname,username,password,email,phoneno;
    EditText fname,uname,pwd,emailid,phoneNo;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String isDonor="true";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        signlogin=findViewById(R.id.signlogin_button);
        signBtn=findViewById(R.id.signpBtn);
        mAuth = FirebaseAuth.getInstance();

        signlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(SignUp.this,Login.class);
                startActivity(intent);
                finish();
            }

        });
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fullname=findViewById(R.id.fullname);
                username=findViewById(R.id.username);
                password=findViewById(R.id.password);
                email=findViewById(R.id.email);
                phoneno=findViewById(R.id.phoneNo);
                fname=fullname.getEditText();
                uname=username.getEditText();
                pwd=password.getEditText();
                emailid=email.getEditText();
                phoneNo=phoneno.getEditText();
                String fullName=fname.getText().toString();
                String userName=uname.getText().toString();
                String passWord=pwd.getText().toString();
                String emailID=emailid.getText().toString();
                String phoneNO=phoneNo.getText().toString();
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("DonorData");
                if (TextUtils.isEmpty(fullName) || TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord) || TextUtils.isEmpty(emailID) || TextUtils.isEmpty(phoneNO)) {
                    Toast.makeText(SignUp.this, "Please enter your deatails..", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(emailID,passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Registration successful!",Toast.LENGTH_LONG).show();

                                    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                                    String currentUid=currentFirebaseUser.getUid().toString();

                                    DonorData donordata=new DonorData(currentUid,userName,fullName,phoneNO,isDonor);
                                    databaseReference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            // on below line we are setting data in our firebase database.
                                            databaseReference.child(currentUid).setValue(donordata);
                                            // displaying a toast message.
                                            //Toast.makeText(SignUp.this, "Course Added..", Toast.LENGTH_SHORT).show();
                                            // starting a main activity.
                                            Intent intent=new Intent(SignUp.this,UserHome.class);
                                            startActivity(intent);
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            // displaying a failure message on below line.
                                            //Toast.makeText(SignUp.this, "Fail to add Course..", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    //Toast.makeText(getApplicationContext(),currentUid,Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    // Registration failed
                                    Toast.makeText(getApplicationContext(),"Registration failed!!" + " Please try again later",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}