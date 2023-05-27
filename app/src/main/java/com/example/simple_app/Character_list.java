package com.example.simple_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Character_list extends Fragment {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    public View view;

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.view = view;
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();


        GetDataService service = RetrofitClient.getRetrofitInstance().create(GetDataService.class);
        Call<List<DataNameImg>> call = service.getAllInfo();
        call.enqueue(new Callback<List<DataNameImg>>() {
            @Override
            public void onResponse(Call<List<DataNameImg>> call, Response<List<DataNameImg>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<DataNameImg>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(getContext().getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void generateDataList(List<DataNameImg> photoList) {
        recyclerView = view.findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(getContext().getApplicationContext(), photoList, new CustomAdapter.ItemClickListener() {
            @Override
            public void onItemClick(DataNameImg dataNameImg) {


                goToIntent(dataNameImg.getChar_id());
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public  void goToIntent(int char_id){
        Intent intent = new Intent(getContext(), PersonInfo.class);
        intent.putExtra("char_id", String.valueOf(char_id));
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_list, container, false);
    }
}