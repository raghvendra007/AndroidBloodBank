package com.example.raghvendratiwari.e_bloodbank;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class hospital extends Fragment  {


    public hospital() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // TextView textView=new TextView(getContext());
        //textView.setText("mmu");








        return inflater.inflate(R.layout.fragment_hospital, container, false);
    }

}

