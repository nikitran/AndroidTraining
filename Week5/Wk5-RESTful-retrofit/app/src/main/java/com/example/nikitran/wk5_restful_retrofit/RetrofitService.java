package com.example.nikitran.wk5_restful_retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by nikitran on 2/22/17.
 */

public interface RetrofitService {

    // GitHub Repositories of a given user endpoint
    @GET("users/{username}/repos")
    Call<List<GitHub>> performRequest(@Path("username") String user);
}
