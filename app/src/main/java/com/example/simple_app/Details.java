package com.example.simple_app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Details extends AppCompatActivity {
    //public static final String EXTRA_CONTACT = "id";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        //int position = (Integer)getIntent().getExtras().get(EXTRA_CONTACT);
        //List<DataNameImg> list  = CustomAdapter.dataList;

        /*ImageView photo = (ImageView)findViewById(R.id.photo);
        //photo.setImageResource(list.get(position).getImg());
        photo.setContentDescription(list.get(position).getName());
        TextView name = (TextView)findViewById(R.id.name);
        name.setText(list.get(position).getName());
        TextView number = (TextView)findViewById(R.id.number);
        number.setText(list.get(position).getBirthday());*/
    }
}
