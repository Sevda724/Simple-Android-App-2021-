package com.example.simple_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Change_info extends AppCompatActivity {
    public DBHelper DB;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USSERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    public SharedPreferences sharedPreferences;
    public EditText editText;
    public TextView textView;
    public String content;
    public String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.setInfo);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        DB = new DBHelper(this);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        name = sharedPreferences.getString(KEY_USSERNAME,null);

        HashMap<String, String> data = DB.getInfo(name);

        content = getIntent().getExtras().getString("content","defaultKey");

        if(content.equals("username")){
            editText.setText(name);
            setTitle("Username");
            textView.setText("Fill in your username");

        }else if(content.equals("ns")){
            editText.setText(data.get("nameSurname"));
            setTitle("Name and Surname");
            textView.setText("Fill in your first name and last name");
        }else{
            editText.setText(" ");
        }

    }


    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }


        int id = item.getItemId();
        if(id == R.id.editR){
            AlertDialog.Builder builder = new AlertDialog.Builder(Change_info.this);
            builder.setMessage("Are you sure you want to change your data?");
            builder.setCancelable(true);

            builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if(content.equals("username")){
                        Boolean checkuser = DB.checkusername(String.valueOf(editText.getText()));
                        if (checkuser == false){
                            boolean bool = DB.updateUsername(String.valueOf(editText.getText()),name);
                            if(bool == false){
                                Toast.makeText(Change_info.this, "Failed to update", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(Change_info.this, "Successfully updated!", Toast.LENGTH_SHORT).show();
                                sharedPreferences.edit().putString(KEY_USSERNAME, String.valueOf(editText.getText())).apply();
                                finish();
                            }
                        }else{
                            Toast.makeText(Change_info.this, "This username is already occupied. Enter another one", Toast.LENGTH_SHORT).show();
                        }
                    }else if(content.equals("ns")){
                        boolean bool = DB.updateNameSurname(String.valueOf(editText.getText()),name);
                        if(bool == false){
                            Toast.makeText(Change_info.this, "Failed to update", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(Change_info.this, "Successfully updated!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
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