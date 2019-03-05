package com.humorboy.mvpapplication.model;

import com.humorboy.mvpapplication.http.HttpHelper;
import com.humorboy.mvpapplication.test.model.DailyBeforeListBean;
import com.humorboy.mvpapplication.test.model.DailyListBean;

import java.util.List;

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
    public Flowable<DailyListBean> fetchDailyListInfo() {
        return mHttpHelper.fetchDailyListInfo();
    }

    @Override
    public Flowable<DailyBeforeListBean> fetchDailyBeforeListInfo(String date) {
        return mHttpHelper.fetchDailyBeforeListInfo(date);
    }

}
