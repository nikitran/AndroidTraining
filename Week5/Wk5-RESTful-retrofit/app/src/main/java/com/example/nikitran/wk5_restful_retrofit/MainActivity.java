package com.example.nikitran.wk5_restful_retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.github.com/";
    private static final String USER = "pepe-romeros";
    private static final String TAG = "MainActivity";
    private TextView resultTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTV = (TextView) findViewById(R.id.text);
        resultTV.setText("Result: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrofitCall();
    }

    private void retrofitCall() {
        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Call<List<GitHub>> result = service.performRequest(USER);

        result.enqueue(new Callback<List<GitHub>>() {
            @Override
            public void onResponse(Call<List<GitHub>> call, Response<List<GitHub>> response) {
                if(response.isSuccessful()){
                    // print result
                    for(GitHub repo:response.body()){
                        resultTV.append(repo.toString());
                        Log.d(TAG, "onResponse: " + repo.toString());
                    }
                } else {
                    // We communicated with the server but incorrectly
                    Log.e(TAG, "onResponse: Error response code" + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<GitHub>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
