package com.example.amitrai.demomusicplayer.backend;

/**
 * Created by amitrai on 22/12/16.
 */

public interface ApiResponseListener {
    public void onApiSuccess(String response);
    public void onApiError(String error);
}
