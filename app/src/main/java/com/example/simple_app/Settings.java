package com.example.simple_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;


public class Settings extends Fragment {
    public  Button button, button1, buttonEdit;
    public View mainView;
    public static boolean click = false;


    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        button = view.findViewById(R.id.buttonBack);
        button1 = view.findViewById(R.id.buttonShare);
        buttonEdit = view.findViewById(R.id.buttonEdit);

        button.setOnClickListener(view1 -> {

            int def = AppCompatDelegate.getDefaultNightMode();
            System.out.println(def + "fghdjksl");
            System.out.println(AppCompatDelegate.MODE_NIGHT_YES);
            System.out.println(AppCompatDelegate.MODE_NIGHT_NO);
            if(def == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                System.out.println("No");
                startActivity(new Intent(getContext(), MainPage.class));
            }else if(def == AppCompatDelegate.MODE_NIGHT_NO) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                startActivity(new Intent(getContext(), MainPage.class));
            }
        });


        button1.setOnClickListener(view1 -> {
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "FinalProject");
                String shareMessage= "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch(Exception e) {

            }
        });

        buttonEdit.setOnClickListener(view1 -> {
            Intent editIntent = new Intent(getContext(), EditProfileInformation.class);
            startActivity(editIntent);

        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
}