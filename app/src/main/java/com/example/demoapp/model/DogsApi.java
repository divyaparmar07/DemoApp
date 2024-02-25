package com.example.demoapp.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DogsApi {
    @GET("/DevTides/DogsApi/master/dogs.json")
    Call<List<DogBreed>> getDogs();
}
