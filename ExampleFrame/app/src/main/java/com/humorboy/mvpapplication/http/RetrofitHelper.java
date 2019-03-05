package com.humorboy.mvpapplication.http;

import com.humorboy.mvpapplication.http.api.Apis;
import com.humorboy.mvpapplication.test.model.DailyBeforeListBean;
import com.humorboy.mvpapplication.test.model.DailyListBean;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class RetrofitHelper implements HttpHelper{
    private Apis mApiService;
    @Inject
    public RetrofitHelper(Apis mApiService) {
        this.mApiService = mApiService;
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
