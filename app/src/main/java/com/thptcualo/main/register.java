package com.thptcualo.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.thptcualo.main.chatappults.Constants;
import com.thptcualo.main.chatappults.PreferenceManager;

import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Pattern;

public class register extends AppCompatActivity{
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mAuth = FirebaseAuth.getInstance();
        MaterialButton logintab  = (MaterialButton) findViewById(R.id.loginbutton);
        preferenceManager = new PreferenceManager(getApplicationContext());
        logintab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this,MainActivity.class);
                startActivity(intent);
            }
        });
        TextView email = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        TextView repassword = (TextView) findViewById(R.id.repassword);
        TextView name = (TextView) findViewById(R.id.name);
        MaterialButton regsis = (MaterialButton) findViewById(R.id.signup);
        regsis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Semail = email.getText().toString().trim();
                String Spassword =  password.getText().toString().trim();
                String Srepassword = repassword.getText().toString().trim();
                String fullname = name.getText().toString().trim();
                //System.out.println("onCLickSignUp");
                if(Semail.isEmpty()||Spassword.isEmpty()|| !Spassword.equals(Srepassword)||!Patterns.EMAIL_ADDRESS.matcher(Semail).matches()){
                    Toast.makeText( register.this, "Đăng kí thất bại do somethings",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(register.this,register.class);
                    startActivity(intent);
                }else{
                    mAuth.createUserWithEmailAndPassword(Semail,Spassword)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task){
                                    if(task.isSuccessful()){
                                        //Toast.makeText(register.this,"Users Registered",Toast.LENGTH_SHORT).show();
                                        User user = new User(fullname,Semail,Spassword);
                                        /*
                                        FirebaseFirestore database = FirebaseFirestore.getInstance();
                                        HashMap<String,Object> user = new HashMap<>();
                                        user.put("fullname",fullname);
                                        user.put("email",email);
                                        user.put("password",Spassword);
                                        database.collection("users")
                                                .add(user)
                                                .addOnSuccessListener(documentReference -> {
                                                    preferenceManager.putBoolean("isSignedIn", true);
                                                    preferenceManager.putString("userId",documentReference.getId());
                                                    preferenceManager.putString("fullname",fullname);
                                                    Toast.makeText(register.this,"Users reged",Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(register.this,MainActivity.class);
                                                    startActivity(intent);
                                                })
                                                .addOnFailureListener(exception ->{
                                                    Toast.makeText(register.this,"faileddd LLL",Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(register.this,register.class);
                                                    startActivity(intent);
                                                });

                                         */

                                        FirebaseDatabase.getInstance("https://somethings-great-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(register.this,"Users reged",Toast.LENGTH_SHORT).show();
                                                    preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN,true);
                                                    preferenceManager.putString(Constants.KEY_USER_ID,FirebaseAuth.getInstance().getCurrentUser().getUid());
                                                    preferenceManager.putString(Constants.KEY_NAME,fullname);
                                                    Intent intent = new Intent(register.this,MainActivity.class);
                                                    startActivity(intent);
                                                } else{
                                                    Toast.makeText(register.this,"faileddd LLL",Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(register.this,register.class);
                                                }
                                            }
                                        });

                                    }else{
                                        Toast.makeText(register.this,"Users Registered Fail"+Spassword+" "+Semail,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(register.this,register.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                }
            }
        });
    }
}