package com.example.mytask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false));
        if(NetworkConnection.isNetworkAvailable(getBaseContext())) {
            getResponse();
        }
        else {
            NetworkConnection.noNetworkError(MainActivity.this, MainActivity.class);
        }
    }

    private void getResponse() {

       NetworkConnection.showLoader(this);
        APIClient Request = APIClient.getapiClient();
        Call<JsonArray> call = Request.getapiInterface().getResponseData();
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                NetworkConnection.progressDialog.dismiss();

                List<Object> res=new ArrayList<>();
                if (response.body() != null) {
                    for (int i=0;i<response.body().size();i++){
                        res.add(response.body().get(i));
                    }
                }

                RecyclerAdapter recyclerAdapter=new RecyclerAdapter(getBaseContext(),res);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                NetworkConnection.progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Failed to Load Data", Toast.LENGTH_SHORT).show();
            }
        });
    }


}