package com.example.raghvendratiwari.e_bloodbank;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class myAdapter  extends RecyclerView.Adapter<myAdapter.ViewHolder> {
   // private List<listItem> listItems;
    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<String> nameList;
    private ArrayList<String> numberList;
    private ArrayList<String> addressList;
    private ArrayList<String> emailList;
    private ArrayList<String> bloodList;


    public myAdapter(Context context, ArrayList<String> nameList,ArrayList<String> numberList,ArrayList<String> addressList,  ArrayList<String> emailList,  ArrayList<String> bloodList) {
        this.nameList = nameList;
        this.numberList = numberList;
        this.bloodList = bloodList;
        this.addressList = addressList;
        this.emailList = emailList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
       // listItem listItem=listItems.get(i);
        viewHolder.item_name.setText(nameList.get(i));
        viewHolder.item_Email.setText(emailList.get(i));
        viewHolder.item_number.setText(numberList.get(i));
        viewHolder.item_address.setText(addressList.get(i));
        viewHolder.item_bloodgroup.setText(bloodList.get(i));




    }


    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView item_name;
        public TextView item_Email;
        public TextView item_number;
        public TextView item_address;
        public TextView item_bloodgroup;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           item_name =(TextView) itemView.findViewById(R.id.item_name);
            item_Email=(TextView) itemView.findViewById(R.id.item_Email);
            item_number=(TextView) itemView.findViewById(R.id.item_number);
            item_address=(TextView) itemView.findViewById(R.id.item_address);
            item_bloodgroup=(TextView) itemView.findViewById(R.id.item_bloodgroup);
        }
    }



}
