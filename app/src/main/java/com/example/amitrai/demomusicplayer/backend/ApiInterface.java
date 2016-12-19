package com.example.amitrai.demomusicplayer.backend;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by amitrai on 16/12/16.
 */


public interface ApiInterface {
//    @GET("movie/top_rated")
//    Call<Repo> getTopRatedMovies();

    @GET("users/{user}/repos")
    Call<Object> listRepos(@Path("user") String user);

//    @GET("users/{user}/repos")
//    Call<Object> listRepos(@Path("user") String user);
//
//    @GET("users/{user}/repos")
//    Call<Object> listRepos(@Path("user") String user);
//
//    @GET("users/{user}/repos")
//    Call<Object> listRepos(@Path("user") String user);
//
//    @GET("users/{user}/repos")
//    Call<Object> listRepos(@Path("user") String user);
//
//    @GET("users/{user}/repos")
//    Call<Object> listRepos(@Path("user") String user);
//
//    @GET("users/{user}/repos")
//    Call<Object> listRepos(@Path("user") String user);
//
//    @GET("users/{user}/repos")
//    Call<Object> listRepos(@Path("user") String user);



}
