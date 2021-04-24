package com.example.mytask;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APInterface {

    @GET("repositories")
    Call<JsonArray> getResponseData();
}
