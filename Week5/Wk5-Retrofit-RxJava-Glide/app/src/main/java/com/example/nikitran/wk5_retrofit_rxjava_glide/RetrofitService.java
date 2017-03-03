package com.example.nikitran.wk5_retrofit_rxjava_glide;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by nikitran on 2/23/17.
 */


public class RetrofitService {

    // 1. define the factory as static:
    public static class Factory {
        public static final String BASE_URL = "https://randomuser.me/";


        public static Retrofit create(){
           return new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                   .build();
        }

        public static Observable<Example> create (int amount){
            Retrofit retrofit = create();
            ProfileService service = retrofit.create(ProfileService.class);
            return service.performCall(amount);
        }
    }

    // 2. define the interface:
    public interface ProfileService {
        @GET("api/")
        Observable<Example> performCall(@Query("results") int amount);
    }
}

