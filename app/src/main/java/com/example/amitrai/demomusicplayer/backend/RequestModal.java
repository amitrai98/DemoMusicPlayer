package com.example.amitrai.demomusicplayer.backend;

import retrofit2.Call;

/**
 * Created by amitrai on 22/12/16.
 */

public class RequestModal {
    RequestType requestType;
    ApiName apiName;
    Call call;
    ApiResponseListener responseListeners;


    public RequestModal(RequestType requestType,
            ApiName apiName,
            Call call,
            ApiResponseListener responseListeners){

        this.requestType = requestType;
        this.apiName = apiName;
        this.call = call;
        this.responseListeners = responseListeners;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public ApiName getApiName() {
        return apiName;
    }

    public void setApiName(ApiName apiName) {
        this.apiName = apiName;
    }

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }

    public ApiResponseListener getResponseListeners() {
        return responseListeners;
    }

    public void setResponseListeners(ApiResponseListener responseListeners) {
        this.responseListeners = responseListeners;
    }
}
