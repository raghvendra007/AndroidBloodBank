package com.example.raghvendratiwari.e_bloodbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {


    Button Register;

    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);




       Register = (Button)findViewById(R.id.button2);
       Register.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
              Intent intent = new Intent(Main2Activity.this, Main22Activity.class);
               startActivity(intent); }
        });


        final EditText Email = (EditText)findViewById(R.id.GetEmail);
        final EditText Password = (EditText)findViewById(R.id.GetPassword);

        Button Login = (Button)findViewById(R.id.Login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String enteredEmail = Email.getText().toString().trim();
                String enteredPassword = Password.getText().toString().trim();

                 if(!enteredEmail.equals("")){
                     if(Patterns.EMAIL_ADDRESS.matcher(enteredEmail).matches()){

                         if(!Password.equals("")){
                             // Now we know that email and password are entered correctly
                             // time to check if user exits in our database
                             checkLoginDetails(enteredEmail,enteredPassword);
                         }else{
                             Toast.makeText(Main2Activity.this, "Please enter the password.", Toast.LENGTH_SHORT).show();
                         }

                     }else{
                         Toast.makeText(Main2Activity.this, "Enter a valid email.", Toast.LENGTH_SHORT).show();
                     }
                 }
            }
        });
    }

    private void checkLoginDetails(final String Email, final String Password){

     final DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        db.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String retrievedEmail = snapshot.child("Email").getValue(String.class);

                    if(retrievedEmail.equals(Email)){

                        String retrievedPassword = snapshot.child("Password").getValue(String.class);
                        if(retrievedPassword.equals(Password)){

                            String key= snapshot.getKey();

                            Toast.makeText(Main2Activity.this, "key "+key, Toast.LENGTH_SHORT).show();

                            SharedPreferences sharedPreferences=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor =sharedPreferences.edit();
                            editor.putString("Info_key",key);
                            editor.apply();

                            Intent intent = new Intent(Main2Activity.this,Navbar.class);
                            startActivity(intent);
                            Toast.makeText(Main2Activity.this, "Logging in...", Toast.LENGTH_SHORT).show();
                            Main2Activity.this.finish();
                                    // this user has an account in our database i.e. is already registered
                            flag = 1;
                            break;
                        }
                    }

                }

                if(flag == 0){
                    Toast.makeText(Main2Activity.this, "Either your email or password is wrong.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences prefs = getSharedPreferences("userinfo", MODE_PRIVATE);
        String restoredText = prefs.getString("Info_key", "");
        if (!restoredText.equals("Default")){

            Intent intent = new Intent(Main2Activity.this,Navbar.class);
            startActivity(intent);

        }
    }
}
