package com.thptcualo.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.thptcualo.main.chatappults.Constants;
import com.thptcualo.main.chatappults.PreferenceManager;
import com.thptcualo.main.databinding.ActivityMainBinding;
import com.thptcualo.main.databinding.ChatBoxBinding;

public class ChatBox extends AppCompatActivity {
    private ChatBoxBinding binding;
    private PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_box);
        /*
        MaterialButton ble = (MaterialButton) findViewById(R.id.backbuton122);
        ble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatBox.this,Hub.class);
                startActivity(intent);
            }
        });
         */
        binding = ChatBoxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        loadUserDetails();
    }

    private void loadUserDetails() {
        binding.usernameb.setText(preferenceManager.getString(Constants.KEY_NAME));
    }
}