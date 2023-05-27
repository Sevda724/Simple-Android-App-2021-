package com.example.simple_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public EditText username;
    public EditText password;
    public Button signUp;
    public Button login;
    public DBHelper DB;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USSERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    public SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.button);
        signUp = findViewById(R.id.button3);
        username = findViewById(R.id.usernameL);
        password = findViewById(R.id.passwordL);
        DB = new DBHelper(this);

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_USSERNAME,null);
        if(name != null){
            Intent intent = new Intent(this, MainPage.class);
            startActivity(intent);
        }
        signUp.setOnClickListener(View -> {
            Intent intent=new Intent(this,SignUp.class);
            startActivity(intent);
        });

        login.setOnClickListener(View -> {

            checker();
            Date c = Calendar.getInstance().getTime();
            String time = String.valueOf(c);
            System.out.println(time);
        });


    }

    void checker() {
        boolean isValid = true;
        if (isEmpty(username)) {
            username.setError("You must enter username to login!");
            isValid = false;
        }

        if (isEmpty(password)) {
            password.setError("You must enter password to login!");
            isValid = false;
        }

        if (isValid) {
            String usernameValue = username.getText().toString();
            String passwordValue = password.getText().toString();

            Boolean checkuserpass = DB.checkusernamepassword(usernameValue, passwordValue);
            if(checkuserpass){
//                if(AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_NO ||
//                AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_YES) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_USSERNAME, usernameValue);
                editor.putString(KEY_PASSWORD, passwordValue);
                editor.apply();
                Toast.makeText(MainActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();

                Intent intent  = new Intent(getApplicationContext(), MainPage.class);
                startActivity(intent);
                username.setText("");
                password.setText("");
            }else{
                Toast.makeText(MainActivity.this, "Wrong email or password!", Toast.LENGTH_SHORT).show();
                DBHelper info = new DBHelper(this);
                HashMap<String, String> data = info.getInfo("user");
                info.close();
                System.out.println("Password" + data.get("password"));
            }
        }
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
}