package com.example.lab7;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServices {
    @GET("get-fruit-by-id/{id}")
    Call<Fruit> getFruitById(@Path("id") String id);
}
