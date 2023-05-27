package com.example.simple_app;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService {

    @GET("/api/characters")
    Call<List<DataNameImg>> getAllInfo();

    @GET("/api/characters/{id}")
    Call<List<PersonInfoClass>> getByid(@Path("id") String id);
}