package com.example.amitrai.demomusicplayer.backend;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitrai on 20/12/16.
 */

public class RxJavaHandler {
    private CompositeDisposable _disposables;
    private String TAG = getClass().getSimpleName();
    int i = 0;


    public void callApi(){

        Observable.just(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "got call subscribe");
                    }

                    @Override
                    public void onNext(Integer value) {
                        try {
//                            ApiRequester.request(new RequestModal(RequestType.GET,
//                                    ApiName.REGISTER, null, new ResponseListeners() {
//                                @Override
//                                public void onResponseReceived() {
//
//                                }
//
//                                @Override
//                                public void onResponseError() {
//
//                                }
//                            }));
                        }catch (Exception exp){
                            exp.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "got call error");
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "got call oncomplete");
                    }
                });



    }


}
