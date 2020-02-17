package com.s1s1s1.livesearch.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static final String BASE_URL = "http://192.168.0.103/searching/";
    private static Retrofit retrofit;
    private static ApiClient apiClient;


    private ApiClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static synchronized ApiClient getInstance() {
        if (apiClient == null) {
            apiClient = new ApiClient();
        }
        return apiClient;
    }


    public ApiInterface getApiInterface() {
        return retrofit.create(ApiInterface.class);
    }
}
