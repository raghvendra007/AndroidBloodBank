package com.example.raghvendratiwari.e_bloodbank;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class Main22Activity extends AppCompatActivity {
   private EditText  editText3,editText5,editText7,editText6,editText8,editText;
    private FirebaseAuth mAuth;

    private Button Submit;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register2);
        editText3=findViewById(R.id.editText3);
        editText5=findViewById(R.id.editText5);
        editText7=findViewById(R.id.editText7);
        editText6=findViewById(R.id.editText6);
        editText8=findViewById(R.id.editText8);
        editText=findViewById(R.id.editText);
        mAuth = FirebaseAuth.getInstance();

        Submit  = (Button)findViewById(R.id.Submit);
        login  = (Button)findViewById(R.id.login);

//        Button btn = findViewById(R.id.button);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()){
//                    case R.id.button2:
//                        registerUser();
//                        break;
//                    case R.id.button:
//                        startActivity(new Intent(Main22Activity.this, Main2Activity.class));
//                        break;
//                }
//
//            }
//        });


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAndCheckInfo();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Main22Activity.this,Main2Activity.class);
                startActivity(intent);
                Main22Activity.this.finish();

            }
        });

    }
    public void getAndCheckInfo(){

      String name=editText3.getText().toString().trim();
       String email=editText5.getText().toString().trim();
       String address=editText7.getText().toString().trim();
        String phone =editText6.getText().toString().trim();
       String password=editText8.getText().toString().trim();
      String   bloodgroup=editText.getText().toString().trim();

    if(name.isEmpty()){
        editText3.setError("please enter name");
        editText3.requestFocus();
        return;}
    if(email.isEmpty()){
        editText5.setError("please enter email");
        editText5.requestFocus();
        return;

    }
    if( !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
        editText5.setError("please enter valid email");
        editText5.requestFocus();
        return;
    }
    if(address.isEmpty()){
        editText7.setError("please enter address");
        editText7.requestFocus();
        return;
    }
    if(address.length()<10){
        editText7.setError("please enter  correct address with pin ");
        editText7.requestFocus();
        return;

    }
    if(phone.isEmpty()){
        editText6.setError("please enter phone number");
        editText6.requestFocus();
        return;
    }
        if( ! Patterns.PHONE.matcher(phone).matches()){
            editText6.setError("please enter correct  phone number");
            editText6.requestFocus();
            return;

        }
    if(password.isEmpty()){
        editText8.setError("please enter password");
        editText8.requestFocus();
        return;

    }
    if(password.length()<6){
        editText8.setError("please enter password length greater than 6");
        editText8.requestFocus();
        return;

    }
    if(bloodgroup.isEmpty()){
        editText.setError("please blood group");
        editText.requestFocus();
        return;
    }
    if(bloodgroup.length()>4) {
        editText.setError("please enter correct blood group");
        editText.requestFocus();
        return;

    }

/*
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "user registered successfully", Toast.LENGTH_LONG).show();

                    CurrentUser =

                }
            }
        } );
*/
        submitUserDetails( name,email,phone,address,password,bloodgroup);


    }

    private void submitUserDetails(String name, String email, String number, String address, String password,String  bloodgroup){

        Map newUser = new HashMap();
        newUser.put("Name",name);
        newUser.put("Email",email);
        newUser.put("Number",number);
        newUser.put("Address",address);
        newUser.put("Password",password);
        newUser.put("BloodGroup",bloodgroup);

       // Toast.makeText(this, "Method Ran", Toast.LENGTH_SHORT).show();
        
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        String key = db.child("Users").push().getKey();

                db.child("Users").child(key).setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){

                    Toast.makeText(Main22Activity.this, "New User Added", Toast.LENGTH_SHORT).show();

                     Intent intent = new Intent(Main22Activity.this,Main2Activity.class);
                     startActivity(intent);
                     Main22Activity.this.finish();

                }else{

                    Toast.makeText(Main22Activity.this, "An unexpected error occurred", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }


}
