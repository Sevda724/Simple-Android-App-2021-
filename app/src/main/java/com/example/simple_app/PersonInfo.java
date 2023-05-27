package com.example.simple_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonInfo extends AppCompatActivity {
    private List<PersonInfoClass> datalist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        setTitle("Information");
        TextView textname = (TextView)findViewById(R.id.textName);
        ImageView imageView = findViewById(R.id.imagePerson1);
        TextView textbirthday = (TextView)findViewById(R.id.textBirthday);
        TextView textstatus = (TextView)findViewById(R.id.textStatus);
        TextView textoccupation = (TextView)findViewById(R.id.textOccupation);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String char_id = getIntent().getExtras().getString("char_id","defaultKey");
        GetDataService service = RetrofitClient.getRetrofitInstance().create(GetDataService.class);
        System.out.println(char_id);
        Call<List<PersonInfoClass>> call = service.getByid(char_id);
        call.enqueue(new Callback<List<PersonInfoClass>>() {
            @Override
            public void onResponse(Call<List<PersonInfoClass>> call, Response<List<PersonInfoClass>> response) {
                datalist = response.body();
                System.out.println(datalist.isEmpty());
                for(PersonInfoClass infoClass: datalist){
                    System.out.println(infoClass.getName());
                    textname.setText(infoClass.getName());
                    Picasso.get()
                            .load(infoClass.getImg())
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(imageView);
                    textbirthday.setText(infoClass.getBirthday());
                    textstatus.setText(infoClass.getStatus());
                    String occ = "";
                    String [] occup = infoClass.getOccupation();
                    for (String o:occup) {
                        occ += o + ". ";
                    }
                    textoccupation.setText(occ);
                }
            }

            @Override
            public void onFailure(Call<List<PersonInfoClass>> call, Throwable t) {
            }
        });



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