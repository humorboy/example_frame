package com.humorboy.mvpapplication.http;

import com.humorboy.mvpapplication.http.api.Apis;
import com.humorboy.mvpapplication.mvp.main.model.DailyBeforeListBean;
import com.humorboy.mvpapplication.mvp.main.model.DailyListBean;
import com.humorboy.mvpapplication.mvp.welcome.model.WelcomeBean;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class RetrofitHelper implements HttpHelper{
    private Apis mApiService;
    @Inject
    public RetrofitHelper(Apis mApiService) {
        this.mApiService = mApiService;
    }



    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mApiService.getWelcomeInfo(res);
    }

    @Override
    public Flowable<DailyListBean> fetchDailyListInfo() {
        return mApiService.getDailyList();
    }

    @Override
    public Flowable<DailyBeforeListBean> fetchDailyBeforeListInfo(String date) {
        return mApiService.getDailyBeforeList(date);
    }
}
