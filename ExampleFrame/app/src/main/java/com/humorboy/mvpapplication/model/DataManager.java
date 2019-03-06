package com.humorboy.mvpapplication.model;

import com.humorboy.mvpapplication.http.HttpHelper;
import com.humorboy.mvpapplication.mvp.main.model.DailyBeforeListBean;
import com.humorboy.mvpapplication.mvp.main.model.DailyListBean;
import com.humorboy.mvpapplication.mvp.welcome.model.WelcomeBean;

import io.reactivex.Flowable;

/**
 * @author: Est <codeest.dev@gmail.com>
 * @date: 2017/4/21
 * @desciption:
 */

    public class DataManager implements HttpHelper {
    HttpHelper mHttpHelper;
    public DataManager(HttpHelper httpHelper) {
        mHttpHelper = httpHelper;
    }

    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mHttpHelper.fetchWelcomeInfo(res);
    }

    @Override
    public Flowable<DailyListBean> fetchDailyListInfo() {
        return mHttpHelper.fetchDailyListInfo();
    }

    @Override
    public Flowable<DailyBeforeListBean> fetchDailyBeforeListInfo(String date) {
        return mHttpHelper.fetchDailyBeforeListInfo(date);
    }

}
