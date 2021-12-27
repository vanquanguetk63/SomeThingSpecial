package com.thptcualo.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class CreateHelpInfo extends AppCompatActivity {
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_help_info);
        AppCompatButton backbuton = (AppCompatButton) findViewById(R.id.backbuton);
        backbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateHelpInfo.this, Hub.class);
                startActivity(intent);
            }
        });


        RadioButton r1C = (RadioButton) findViewById(R.id.rbuton1);
        RadioButton r2C = (RadioButton) findViewById(R.id.rbuton2);
        RadioButton r3C = (RadioButton) findViewById(R.id.rbuton3);

        TextView detai = (TextView) findViewById(R.id.noidinput);
        TextView sdtt = (TextView) findViewById(R.id.sdt);
        TextView addes = (TextView) findViewById(R.id.adress);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        AppCompatButton butn = (AppCompatButton) findViewById(R.id.confirmedinfo);
        butn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CreateHelpInfo.this, "LMAO", Toast.LENGTH_SHORT).show();
                String Ssdt = sdtt.getText().toString();
                String Sdetail = detai.getText().toString();
                String Saddress = addes.getText().toString();
                String r1, r2, r3;
                if (r1C.isChecked()) {
                    r1 = "true";
                } else {
                    r1 = "false";

                }

                if (r2C.isChecked()) {
                    r2 = "true";
                } else {
                    r2 = "false";
                }

                if (r3C.isChecked()) {
                    r3 = "true";
                } else {
                    r3 = "false";
                }
                if (ActivityCompat.checkSelfPermission(CreateHelpInfo.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //getLocation();
                } else {
                    //
                    ActivityCompat.requestPermissions(CreateHelpInfo.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
                CreateHelpForm createHelpForm = new CreateHelpForm(r1, r2, r3, Sdetail, Ssdt, Saddress);
                FirebaseDatabase.getInstance("https://somethings-great-default-rtdb.asia-southeast1.firebasedatabase.app")
                        .getReference("UsersNeedHelp")
                        .child(FirebaseAuth.getInstance().getUid())
                        .setValue(createHelpForm);
            }


        });
    }

}