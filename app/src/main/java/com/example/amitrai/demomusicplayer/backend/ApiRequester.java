package com.example.amitrai.demomusicplayer.backend;

import android.util.Log;

import com.example.amitrai.demomusicplayer.modals.Repo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by amitrai on 15/12/16.
 */

public class ApiRequester {

    private static final String TAG = ApiRequester.class.getSimpleName();

    public interface GitHubService {
        @GET("users/{user}/repos")
        Call<List<Repo>> listRepos(@Path("user") String user);
    }


    public static final String API_URL = "https://api.github.com";

    public static class Contributor {
        public final String login;
        public final int contributions;

        public Contributor(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }
    }

    public interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo);
    }

    public static void request(RequestModal requestModal, final ApiResponseListener responseListener){

        try {
            requestModal.getCall().enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        ResponseBody body = response.body();
                        String response_string = body.string();
                        responseListener.onApiSuccess(response_string);
                    }catch (Exception exp){
                        exp.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e(TAG, "response"+t);
                    responseListener.onApiError(t.getMessage());
                }
            });
        }catch (Exception exp){
            exp.printStackTrace();
        }
    }
}
