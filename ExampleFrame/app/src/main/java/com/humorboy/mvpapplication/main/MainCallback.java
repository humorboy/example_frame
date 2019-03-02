package com.humorboy.mvpapplication.main;

public interface MainCallback {
     void onSuccess(Object data);
     void onFailure(Object data);
     void onComplete();
     void onError();
}
