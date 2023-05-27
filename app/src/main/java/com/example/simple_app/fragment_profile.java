package com.example.simple_app;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;


public class fragment_profile extends Fragment {
    public SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USSERNAME = "username";

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tx = view.findViewById(R.id.IDUserr);
        TextView tx2 = view.findViewById(R.id.IDName);
        TextView tx3 = view.findViewById(R.id.IDDate);
        TextView tx4 = view.findViewById(R.id.IDEmail);

        sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String user = sharedPreferences.getString(KEY_USSERNAME,null);
        DBHelper info = new DBHelper(getContext());
        HashMap<String, String> data = info.getInfo(user);
        info.close();
        tx.setText(user);
        tx2.setText(data.get("nameSurname"));
        tx3.setText(data.get("date"));
        tx4.setText(data.get("email"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}