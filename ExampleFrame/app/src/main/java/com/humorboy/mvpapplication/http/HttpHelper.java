package com.humorboy.mvpapplication.http;

import com.humorboy.mvpapplication.mvp.main.model.DailyBeforeListBean;
import com.humorboy.mvpapplication.mvp.main.model.DailyListBean;
import com.humorboy.mvpapplication.mvp.welcome.model.WelcomeBean;

import io.reactivex.Flowable;

public interface HttpHelper {
    Flowable<WelcomeBean> fetchWelcomeInfo(String res);
    Flowable<DailyListBean> fetchDailyListInfo();
    Flowable<DailyBeforeListBean> fetchDailyBeforeListInfo(String date);
}
