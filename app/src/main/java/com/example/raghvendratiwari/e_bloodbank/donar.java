package com.example.raghvendratiwari.e_bloodbank;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class donar extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<String> nameList;
    private ArrayList<String> numberList;
    private ArrayList<String> addressList;
    private ArrayList<String> emailList;
    private ArrayList<String> bloodList;


    public donar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_donar, container, false);
        recyclerView=(RecyclerView)inflate. findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        nameList =new ArrayList<>();
        addressList =new ArrayList<>();
        emailList =new ArrayList<>();
        bloodList =new ArrayList<>();
        numberList =new ArrayList<>();


        DatabaseReference db= FirebaseDatabase.getInstance().getReference().child("Users");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String name = snapshot.child("Name").getValue(String.class);
                    String number = snapshot.child("Number").getValue(String.class);
                    String email = snapshot.child("Email").getValue(String.class);
                    String address = snapshot.child("Address").getValue(String.class);
                    String blood = snapshot.child("BloodGroup").getValue(String.class);


                    numberList.add(number);
                    nameList.add(name);
                    emailList.add(email);
                    addressList.add(address);
                    bloodList.add(blood);

                }

                myAdapter adapter = new myAdapter(getContext(),nameList,numberList,addressList,emailList,bloodList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return inflate;
    }

}
