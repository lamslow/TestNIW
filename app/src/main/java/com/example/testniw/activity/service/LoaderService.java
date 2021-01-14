package com.example.testniw.activity.service;

import com.example.testniw.activity.model.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoaderService {

    @GET("users/google/repos")
    Call<List<Example>> getAll(@Query("page") int page,
                              @Query("per_page") int per_page);
}
