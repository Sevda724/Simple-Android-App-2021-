package com.example.simple_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class SignUp extends AppCompatActivity {
    public EditText username;
    public EditText password;
    public EditText email;
    public EditText nameSurname;
    public Button create;
    public DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username = findViewById(R.id.usernameSu);
        password = findViewById(R.id.PasswordSu);
        email = findViewById(R.id.EmailSu);
        nameSurname = findViewById(R.id.namaSurname);
        DB = new DBHelper(this);
        create = findViewById(R.id.create);
        create.setOnClickListener(View -> {
            checker();
        });

    }

    void checker() {
        boolean isValid = true;
        if (isEmpty(username)) {
            username.setError("You must enter username!");
            isValid = false;
        }
        if (!isEmail(email)) {
            email.setError("Enter valid email");
            isValid = false;
        }
        if(isEmpty(nameSurname)){
            nameSurname.setError("You must enter name and surname!");
        }

        if (isEmpty(password)) {
            password.setError("You must enter password!");
            isValid = false;
        } else {
            if (password.getText().toString().length() < 4) {
                password.setError("Password must be at least 4 chars long!");
                isValid = false;
            }
        }
        String user = username.getText().toString();
        String pass = password.getText().toString();
        String emailText = email.getText().toString();
        String nameSur = nameSurname.getText().toString();
        Date c = Calendar.getInstance().getTime();
        String time = String.valueOf(c);

        Boolean checkuser = DB.checkusername(user);
        if (isValid) {
            if(checkuser==false){
                System.out.println(time);
            Boolean insert = DB.insertData(user,pass,emailText, nameSur, time);
            if (insert == true) {
                Toast.makeText(SignUp.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(SignUp.this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        } else {
                Toast.makeText(SignUp.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
            }
        }
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