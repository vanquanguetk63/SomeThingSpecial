package com.thptcualo.main;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.resources.MaterialAttributes;
import com.google.firebase.auth.FirebaseAuth;
import com.thptcualo.main.chatappults.Constants;
import com.thptcualo.main.chatappults.PreferenceManager;
import com.thptcualo.main.databinding.Tab5Binding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab5 extends Fragment {
    private FirebaseAuth mAuth;
    //Tab5Binding binding;
    private PreferenceManager preferenceManager;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tab5() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab5.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab5 newInstance(String param1, String param2) {
        Tab5 fragment = new Tab5();
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

   //     MaterialButton signupbutton = (MaterialButton) get ;
     //   signupbutton.findViewById(R.id.signout);
    //   mAuth = FirebaseAuth.getInstance();
     /*   signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(getActivity(),"signed out",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),MainActivity.class);
                //startActivity(intent);
            }
        });*/
/*        MaterialButton displayinfor = (MaterialButton) getView().findViewById(R.id.displayinfor);

        displayinfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Infor: " +FirebaseAuth.getInstance()
                        .getCurrentUser()
                        .getDisplayName(),Toast.LENGTH_SHORT).show();
            }
        });
*/

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatButton logoutbn = (AppCompatButton) getView().findViewById(R.id.logout);
        logoutbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getActivity(),"signed out",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });

        AppCompatButton changepass = (AppCompatButton) getView().findViewById(R.id.editpassword);
        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });
        AppCompatButton notimage = (AppCompatButton) getView().findViewById(R.id.notimanage);
        notimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });
        AppCompatButton infoedit = (AppCompatButton) getView().findViewById(R.id.editinfor);
        infoedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });
         /*
        MaterialButton displayinfor = (MaterialButton) getView().findViewById(R.id.displayinfor);

        displayinfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Infor: " +FirebaseAuth.getInstance()
                        .getCurrentUser()
                        .getEmail(),Toast.LENGTH_SHORT).show();
            }
        });
        MaterialButton signupbutton = (MaterialButton)  getView().findViewById(R.id.signout);
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getActivity(),"signed out",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });

         */


    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        preferenceManager = new PreferenceManager(getActivity().getApplicationContext());
        Tab5Binding binding = Tab5Binding.inflate(inflater,container,false);
        binding.usernamea.setText(preferenceManager.getString(Constants.KEY_NAME));
        return binding.getRoot();
        //return view;
    }

}