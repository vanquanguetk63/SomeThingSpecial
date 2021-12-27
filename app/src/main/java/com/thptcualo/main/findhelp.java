package com.thptcualo.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class findhelp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findhelp);
        AppCompatButton ble = (AppCompatButton) findViewById(R.id.backbuton1);

        ble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(findhelp.this,Hub.class);
                startActivity(intent);
            }
        });

        AppCompatButton bla = (AppCompatButton) findViewById(R.id.confirmedinfoe);

        bla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(findhelp.this,chatscreen.class);
                startActivity(intent);
            }
        });

    }
}