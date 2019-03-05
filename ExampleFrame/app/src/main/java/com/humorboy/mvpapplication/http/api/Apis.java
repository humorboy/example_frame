package com.humorboy.mvpapplication.http.api;

import com.humorboy.mvpapplication.test.model.DailyListBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface Apis {
    String HOST = "http://news-at.zhihu.com/api/4/";

    /**
     * 最新日报
     */
    @GET("news/latest")
    Flowable<DailyListBean> getDailyList();
}
