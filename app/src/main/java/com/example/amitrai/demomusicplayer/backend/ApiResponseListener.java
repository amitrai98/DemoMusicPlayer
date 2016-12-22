package com.example.amitrai.demomusicplayer.backend;

/**
 * Created by amitrai on 22/12/16.
 */

public interface ApiResponseListener {
    public void onApiSuccess(ResponseModal responseModal);
    public void onApiError(String error);
    public void onError(String errormessage);
}
