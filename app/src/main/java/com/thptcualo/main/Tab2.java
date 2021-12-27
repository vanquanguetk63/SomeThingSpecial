package com.thptcualo.main;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.Preference;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.thptcualo.main.adapters.UsersAdapter;
import com.thptcualo.main.chatappults.Constants;
import com.thptcualo.main.chatappults.PreferenceManager;
import com.thptcualo.main.databinding.ActivityMainBinding;
import com.thptcualo.main.databinding.Tab2Binding;
import com.thptcualo.main.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String lmeo;
    private Tab2Binding binding;
    private PreferenceManager preferenceManager;
    private String email;
    //private PreferenceManager preferenceManager;
    private String fullname;
    //Tab2Binding binding;
    private RecyclerView messagesRecyclerView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tab2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab2.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab2 newInstance(String param1, String param2) {
        Tab2 fragment = new Tab2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getUsers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //preferenceManager = new PreferenceManager(getApplicationContext());
        // Inflate the layout for this fragment
        binding=Tab2Binding.inflate(inflater,container,false);

        preferenceManager = new PreferenceManager(getActivity().getApplicationContext());

        getToken();

        return binding.getRoot();
        //return inflater.inflate(R.layout.tab2, container, false);
    }

    private void getToken(){
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(this::updateToken);
    }

    private void updateToken(String token) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://somethings-great-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Users");
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("keyfcm").setValue(token);
    }

    private void getUsers(){
        loading(true);
        DatabaseReference db = FirebaseDatabase.getInstance("https://somethings-great-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Users");
        //db.getReference().child("User").get().addOnCompleteListener(this::onComplete);
        loading(false);
        List<com.thptcualo.main.models.User> users = new ArrayList<>();
        //String CurUserId = preferenceManager.getString(Constants.KEY_EMAIL);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://somethings-great-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
        databaseReference.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("email").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()){
                    //
                }
                else{
                    //preferenceManager.putString(Constants.KEY_NAME,String.valueOf(task.getResult().getValue()));
                    lmeo = String.valueOf(task.getResult().getValue());

                    Log.d("firebasename",String.valueOf(task.getResult().getValue()));
                }
            }
        });

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    com.thptcualo.main.models.User user = new com.thptcualo.main.models.User();
                    //Log.d("LOGGING!", (String) dataSnapshot.getValue());
                    //logger.info("TESTER" , dataSnapshot.getValue());
                    //user.name = "TEST";
                    //user.email = "TEST EMAIL";
                    //user.token = "000000000";
                    user = dataSnapshot.getValue(User.class);
                    user.email=null;
                    //Log.d("LOGTEST", CurUserId);

                    if(!user.email.equals(lmeo)) {
                        users.add(user);
                    }

                    //Log.d("LMEO", "onDataChange: CHANGED");

                    //user.name = (String) dataSnapshot.getValue();
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


}