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

public class SignUpFund extends AppCompatActivity {
    Button signloginfund;
    Button signBtn;
    TextInputLayout fullname,username,password,email,phoneno,details,amount,BankName,bankAcc,IFSCcode;
    EditText fullnameE,usernameE,passwordE,emailE,phonenoE,detailsE,amountE,BankNameE,bankAccE,IFSCcodeE;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference dataBaseReference;
    String isDonor="no";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up_fund);

        signloginfund=findViewById(R.id.signloginfund_button);
        signBtn=findViewById(R.id.signinBtn);

        signloginfund.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent=new Intent(SignUpFund.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth = FirebaseAuth.getInstance();
                fullname=findViewById(R.id.name);
                username=findViewById(R.id.username);
                password=findViewById(R.id.password);
                email=findViewById(R.id.email);
                phoneno=findViewById(R.id.phoneNo);
                details=findViewById(R.id.que);
                amount=findViewById(R.id.money);
                BankName=findViewById(R.id.bank);
                bankAcc=findViewById(R.id.bankacct);
                IFSCcode=findViewById(R.id.bankifsc);

                fullnameE=fullname.getEditText();
                usernameE=username.getEditText();
                passwordE=password.getEditText();
                emailE=email.getEditText();
                phonenoE=phoneno.getEditText();
                detailsE=details.getEditText();
                amountE=amount.getEditText();
                BankNameE=BankName.getEditText();
                bankAccE=bankAcc.getEditText();
                IFSCcodeE=IFSCcode.getEditText();
                String fullnameS=fullnameE.getText().toString();
                String usernameS=usernameE.getText().toString();
                String passwordS=passwordE.getText().toString();
                String emailS=emailE.getText().toString();
                String phonenoS=phonenoE.getText().toString();
                String detailsS=detailsE.getText().toString();
                String amountS=amountE.getText().toString();
                String BankNameS=BankNameE.getText().toString();
                String bankAccS=bankAccE.getText().toString();
                String IFSCcodeS=IFSCcodeE.getText().toString();
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("RecieverData");
                if (TextUtils.isEmpty(fullnameS) || TextUtils.isEmpty(usernameS) || TextUtils.isEmpty(passwordS)
                        || TextUtils.isEmpty(emailS)|| TextUtils.isEmpty(phonenoS) ||
                        TextUtils.isEmpty(detailsS) || TextUtils.isEmpty(amountS)|| TextUtils.isEmpty(BankNameS) ||
                        TextUtils.isEmpty(bankAccS) || TextUtils.isEmpty(IFSCcodeS)) {
                    Toast.makeText(SignUpFund.this, "Please enter your deatails..", Toast.LENGTH_SHORT).show();
                    return;
                }
                System.out.println(emailS);
                System.out.println(passwordS);
                mAuth.createUserWithEmailAndPassword(emailS,passwordS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Registration successful!",Toast.LENGTH_LONG).show();

                            FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                            String currentUid=currentFirebaseUser.getUid().toString();
                            AddUserData(currentUid,usernameS,fullnameS,phonenoS,detailsS,amountS,BankNameS,bankAccS,IFSCcodeS);

                            Toast.makeText(getApplicationContext(),currentUid,Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(SignUpFund.this,UserHome.class);
                            startActivity(intent);
                            return;
                        }
                        else {
                            // Registration failed
                            Toast.makeText(getApplicationContext(),task.toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
    public void AddUserData(String currentUid,String usernameS,String fullnameS,String phonenoS,String detailsS,String amountS,String BankNameS,String bankAccS,String IFSCcodeS){

        RecieverDetails recieverdetails=new RecieverDetails(currentUid,usernameS,fullnameS,phonenoS,isDonor,detailsS,amountS,BankNameS,bankAccS,IFSCcodeS);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // on below line we are setting data in our firebase database.
                databaseReference.child(currentUid).setValue(recieverdetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
                // displaying a toast message.

                // starting a main activity.
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // displaying a failure message on below line.
                Toast.makeText(SignUpFund.this, "Fail to add Course..", Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }}