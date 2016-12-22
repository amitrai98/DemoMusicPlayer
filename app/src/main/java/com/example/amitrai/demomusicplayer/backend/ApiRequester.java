package com.example.amitrai.demomusicplayer.backend;

import android.util.Log;

import com.example.amitrai.demomusicplayer.modals.Repo;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
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

    public static void request(RequestModal requestModal) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();


        GitHubService service = retrofit.create(GitHubService.class);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Object> call = apiService.listRepos("amitrai98");
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object body = response.body();
                Log.e(TAG, "response"+body);

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e(TAG, "response"+t);
            }
        });


    }
}
