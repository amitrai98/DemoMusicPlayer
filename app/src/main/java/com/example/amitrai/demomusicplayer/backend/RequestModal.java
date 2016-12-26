package com.example.amitrai.demomusicplayer.backend;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by amitrai on 22/12/16.
 */

public class RequestModal {
    RequestType requestType;
    ApiName apiName;
    Call<ResponseBody> call;


    public RequestModal(RequestType requestType,
                        ApiName apiName,
                        Call<ResponseBody> call){

        this.requestType = requestType;
        this.apiName = apiName;
        this.call = call;
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
}
