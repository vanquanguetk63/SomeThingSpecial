package com.thptcualo.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thptcualo.main.chatappults.Constants;
import com.thptcualo.main.chatappults.PreferenceManager;
import com.thptcualo.main.databinding.ActivityMainBinding;

import org.w3c.dom.Text;

import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    private TextView banner,registerUser;
    private EditText editTextFullName, editTextAge, editTextEmail, editTextPassword;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private PreferenceManager preferenceManager;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferenceManager = new PreferenceManager(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        MaterialButton signupbutton = (MaterialButton) findViewById((R.id.signupbutton));
        //loading(false);
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,register.class);
                startActivity(intent);
            }
        });

        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.login);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usern =  username.getText().toString().trim();
                String pass =  password.getText().toString().trim();
                mAuth.signInWithEmailAndPassword(usern,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://somethings-great-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
                            databaseReference.child("Users").child(mAuth.getCurrentUser().getUid()).child("fullname").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if(!task.isSuccessful()){
                                        //
                                    }
                                    else{
                                        preferenceManager.putString(Constants.KEY_NAME,String.valueOf(task.getResult().getValue()));
                                        Log.d("firebasename",String.valueOf(task.getResult().getValue()));
                                    }
                                }
                            });
                            preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN,true);
                            preferenceManager.putString(Constants.KEY_USER_ID,mAuth.getCurrentUser().getUid());
                            Intent intent = new Intent(MainActivity.this,Hub.class);
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });


    }
    /*
    private void loading(Boolean isLoading){
        if(isLoading){
            binding.progressBar1.setVisibility(View.VISIBLE);
        }
        else{
            binding.progressBar1.setVisibility(View.INVISIBLE);
        }
    }

     */
}
