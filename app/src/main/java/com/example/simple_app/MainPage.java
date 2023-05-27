package com.example.simple_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;



public class MainPage extends AppCompatActivity {
    EditText editTextLogin;
    EditText editTextPassword;
    public SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this,R.id.fragmentContainerView);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.character_list,R.id.fragment_profile, R.id.settings).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigation, navController);
        //onCreateOptionsMenu(R.menu.menu_main);
        editTextLogin = findViewById(R.id.usernameL);
        editTextPassword = findViewById(R.id.passwordL);


        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
    }

    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.exit){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainPage.this);
            builder.setMessage("Do you want to exit?");
            builder.setCancelable(true);

            builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.commit();
                    Intent in = new Intent(MainPage.this,MainActivity.class);
                    //finish();
                    startActivity(in);
                }
            });

            builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        return  true;
    }
}