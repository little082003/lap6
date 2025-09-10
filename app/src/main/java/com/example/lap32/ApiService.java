package com.example.lap32;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;



public interface ApiService {
    @GET("posts")  // Endpoint
    Call<List<TextNote>> getTextNote();
}