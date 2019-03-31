package com.example.raghvendratiwari.e_bloodbank;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;



/**
 * A simple {@link Fragment} subclass.
 */
public class profile extends Fragment {

    private TextView mname,memail,mnumber,maddress,mbloodGroup;
    private DatabaseReference mDataref;

    public profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_profile, container, false);

        mname = (TextView)inflate.findViewById(R.id.name);
        memail = (TextView)inflate.findViewById(R.id.email);
        maddress = (TextView)inflate.findViewById(R.id.address);
        mnumber = (TextView)inflate.findViewById(R.id.contact);
        mbloodGroup = (TextView)inflate.findViewById(R.id.Blood_Group);

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences( "userinfo",getContext().MODE_PRIVATE);
        String key = sharedPreferences.getString("Info_key", "");

        mDataref = FirebaseDatabase.getInstance().getReference().child("Users").child(key);
        mDataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("Name").getValue(String.class);
                String email = dataSnapshot.child("Email").getValue(String.class);
                String address = dataSnapshot.child("Address").getValue(String.class);
                String number = dataSnapshot.child("Number").getValue(String.class);
                String blood_group = dataSnapshot.child("BloodGroup").getValue(String.class);

                mname.setText(name);
                memail.setText(email);
                maddress.setText(address);
                mnumber.setText(number);
                mbloodGroup.setText(blood_group);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return inflate;
    }

}
