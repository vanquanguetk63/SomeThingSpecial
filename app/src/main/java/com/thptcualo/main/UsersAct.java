package com.thptcualo.main;

import static com.squareup.okhttp.internal.Internal.logger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.thptcualo.main.adapters.UsersAdapter;
import com.thptcualo.main.chatappults.Constants;
import com.thptcualo.main.chatappults.PreferenceManager;
import com.thptcualo.main.databinding.ActivityUsersBinding;
import com.thptcualo.main.models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersAct extends AppCompatActivity {

    private ActivityUsersBinding binding;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        getUsers();
    }

    private void getUsers(){
        loading(true);
        DatabaseReference db = FirebaseDatabase.getInstance("https://somethings-great-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Users");
        //db.getReference().child("User").get().addOnCompleteListener(this::onComplete);
        loading(false);
        List<User> users = new ArrayList<>();
        String CurUserId = preferenceManager.getString(Constants.KEY_USER_ID);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = new User();
                    //Log.d("LOGGING!", (String) dataSnapshot.getValue());
                    //logger.info("TESTER" , dataSnapshot.getValue());
                    //user.name = "TEST";
                    //user.email = "TEST EMAIL";
                    //user.token = "000000000";

                    user = dataSnapshot.getValue(User.class);

                    //Log.d("LMEO", "onDataChange: CHANGED");

                    //user.name = (String) dataSnapshot.getValue();
                    users.add(user);
                }
                if(users.size()>0){
                    UsersAdapter usersAdapter = new UsersAdapter(users);
                    binding.usersRe.setAdapter(usersAdapter);
                    binding.usersRe.setVisibility(View.VISIBLE);
                }else{
                    showerr();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showerr(){
        binding.TextError.setText(String.format("%s","Null lmao"));
        binding.TextError.setVisibility(View.VISIBLE);
    }

    private void loading(Boolean isLoading){
        if(isLoading){
            binding.progressBar.setVisibility(View.VISIBLE);
        }
        else{
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void onComplete(Task<DataSnapshot> task)
        {
        /*
        loading(false);
        String CurUserId = preferenceManager.getString(Constants.KEY_USER_ID);
        if (task.isSuccessful() && task.getResult() != null) {
            List<User> users = new ArrayList<>();
            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                if (CurUserId.equals(queryDocumentSnapshot.getId())) {
                    continue;
                }
                User user = new User();
                user.name = queryDocumentSnapshot.getString(Constants.KEY_NAME);
                user.email = queryDocumentSnapshot.getString(Constants.KEY_EMAIL);
                user.token = queryDocumentSnapshot.getString("keyfcm");
                users.add(user);
            }
            if (users.size() > 0) {
                UsersAdapter usersAdapter = new UsersAdapter(users);
                binding.usersRe.setAdapter(usersAdapter);
                binding.usersRe.setVisibility(View.VISIBLE);
            } else {
                showerr();
            }
        }

         */
    }
}