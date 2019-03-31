package com.example.raghvendratiwari.e_bloodbank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class Navbar extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private ImageView optionbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navbar);
        optionbutton=findViewById(R.id.imageView);

        loadFragment(new hospital());
        BottomNavigationView navigation = findViewById(R.id.nav);
        navigation.setOnNavigationItemSelectedListener(this);


        optionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(Navbar.this, optionbutton);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.option_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(Navbar.this,"Logout Successfully", Toast.LENGTH_SHORT).show();
                        if(item.getTitle().equals("Logout")){

                            SharedPreferences prefs = getSharedPreferences("userinfo", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();

                            editor.putString("Info_key", "Default");
                            editor.clear();
                            editor.commit();

                            Intent intent = new Intent(Navbar.this,Main2Activity.class);
                            startActivity(intent);
                            Navbar.this.finish();


                        }
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.nav_hospital:
                fragment = new hospital ();
                break;

            case R.id.nav_donar:
                fragment = new donar();
                break;

            case R.id.nav_profile:
                fragment = new profile();
                break;
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
