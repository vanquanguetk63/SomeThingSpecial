package com.thptcualo.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class Hub extends AppCompatActivity {
    MeowBottomNavigation bottomNavigation;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hub);



        bottomNavigation = findViewById(R.id.bottom_nav);

        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_chat_bubble_outline_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_accessibility_new_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_baseline_notifications_none_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.ic_baseline_settings_24));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener(){
            @Override
            public void onShowItem(MeowBottomNavigation.Model item){
                Fragment fragment = null;

                switch (item.getId()){
                    case 1:
                        fragment =  new Tab1();
                        loadFragment(fragment);
                        break;
                    case 2:
                        fragment = new Tab2();
                        loadFragment(fragment);
                        //Intent intent = new Intent(getActivity(),UsersAct.class);
                        //startActivity(intent);
                        //Intent intent = new Intent(Hub.this,ChatBox.class);
                        //startActivity(intent);
                        break;
                    case 3:
                        fragment = new Tab3();
                        loadFragment(fragment);
                        break;
                    case 4:
                        fragment = new Tab4();
                        loadFragment(fragment);
                        break;
                    case 5:
                        fragment = new Tab5();
                        loadFragment(fragment);
                        break;
                }
                //loadFragment(fragment);
            }
        });
        //bottomNavigation.setCount(1,"10");
        bottomNavigation.show(1,true);
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 1:
                        Toast.makeText(getApplicationContext(),
                                "Trang Chủ"
                                ,Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(),"Trò Chuyện",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(),"Bản Đồ",Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(getApplicationContext(),"Thông Báo",Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(getApplicationContext(),"Cài Đặt",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                /*Toast.makeText(getApplicationContext()
                ,"you Reselected" + item.getId()
                ,Toast.LENGTH_SHORT).show();
             */
            }
        });
    }

    private void loadFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }
}