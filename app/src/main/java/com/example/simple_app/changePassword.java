package com.example.simple_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class changePassword extends AppCompatActivity {
    public DBHelper DB;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USSERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    public SharedPreferences sharedPreferences;
    public EditText oldPassword,newPassword;
    public String name;
    public String oldPasswordSrting, newPasswordString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        oldPassword = findViewById(R.id.editTextTextPassword);
        newPassword = findViewById(R.id.editTextTextPassword2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Email");

        DB = new DBHelper(this);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        name = sharedPreferences.getString(KEY_USSERNAME,null);
        HashMap<String, String> data = DB.getInfo(name);
        //setText(data.get("email"));
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
            AlertDialog.Builder builder = new AlertDialog.Builder(changePassword.this);
            builder.setMessage("Are you sure you want to change your data?");
            builder.setCancelable(true);
            builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    boolean check = true;
                    oldPasswordSrting = String.valueOf(oldPassword.getText());
                    newPasswordString = String.valueOf(newPassword.getText());
                    if (isEmpty(newPassword)) {
                        newPassword.setError("You must enter password!");
                        check = false;
                    } else {
                        if (newPasswordString.length() < 4) {
                            newPassword.setError("Password must be at least 4 chars long!");
                            check = false;
                        }
                    }

                    Boolean checkuserpass = DB.checkusernamepassword(name, oldPasswordSrting);
                    if(checkuserpass && check){
                        boolean bool = DB.updatePassword(newPasswordString, name);
                        if (bool == false) {
                            Toast.makeText(changePassword.this, "Failed to update", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(changePassword.this, "Successfully updated!", Toast.LENGTH_SHORT).show();
                            finish();
                        }}else{
                        Toast.makeText(changePassword.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();
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
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
}