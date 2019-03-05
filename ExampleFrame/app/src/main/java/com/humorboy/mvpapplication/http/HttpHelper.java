package com.humorboy.mvpapplication.http;

import com.humorboy.mvpapplication.test.model.DailyBeforeListBean;
import com.humorboy.mvpapplication.test.model.DailyListBean;

import io.reactivex.Flowable;

public interface HttpHelper {
    Flowable<DailyListBean> fetchDailyListInfo();
    Flowable<DailyBeforeListBean> fetchDailyBeforeListInfo(String date);
}
