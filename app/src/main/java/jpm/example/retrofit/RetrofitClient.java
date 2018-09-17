package jpm.example.retrofit;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "http://1millionquest.com/";

    private static Retrofit instance;
    private static ApiClientInterface apiService;

    public static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            apiService = instance.create(ApiClientInterface.class);
        }
        return instance;
    }

    public static ApiClientInterface getApiService() {
        if (apiService == null) getInstance();
        return apiService;
    }
}
