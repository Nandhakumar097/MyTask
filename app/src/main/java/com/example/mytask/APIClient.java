package com.example.mytask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class APIClient {

    private static APIClient apiClient;
    public static Retrofit retrofit;
    private APIClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


    }


    public static synchronized APIClient getapiClient() {
        if (apiClient == null) {
            apiClient = new APIClient();
        }

        return apiClient;
    }

    public APInterface  getapiInterface() {

        return retrofit.create(APInterface.class);
    }


}
