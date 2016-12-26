package com.example.amitrai.demomusicplayer.backend;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.amitrai.demomusicplayer.backend.URL_CONSTANTS.TEST_BASE_URL;

/**
 * Created by amitrai on 16/12/16.
 */

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(TEST_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}