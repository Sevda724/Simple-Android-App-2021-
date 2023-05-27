package com.example.simple_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class EditProfileInformation extends AppCompatActivity {
    public Button btnUsername, btnNS, btnEmail, btnPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Edit information");
        setContentView(R.layout.activity_edit_profile_information);
        btnUsername = findViewById(R.id.changeUsername);
        btnNS = findViewById(R.id.changeNS);
        btnEmail = findViewById(R.id.changeEmail);
        btnPassword = findViewById(R.id.changePassword);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        btnUsername.setOnClickListener(view -> {
          content("username");
        });

        btnNS.setOnClickListener(view -> {
            content("ns");
        });

        btnEmail.setOnClickListener(view -> {
            Intent in = new Intent(EditProfileInformation.this, changeEmail.class);
            startActivity(in);
        });

        btnPassword.setOnClickListener(view -> {
            Intent in = new Intent(EditProfileInformation.this, changePassword.class);
            startActivity(in);
        });
    }
       void content(String cont){
           Intent in = new Intent(EditProfileInformation.this, Change_info.class);
           in.putExtra("content", cont);
           startActivity(in);
       }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



