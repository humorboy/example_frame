package com.humorboy.mvpapplication.test.present;

import android.util.Log;

import com.humorboy.mvpapplication.base.RxPresenter;
import com.humorboy.mvpapplication.model.DataManager;
import com.humorboy.mvpapplication.test.contract.WelcomeContract;
import com.humorboy.mvpapplication.test.model.DailyListBean;
import com.humorboy.mvpapplication.util.RxUtil;
import com.humorboy.mvpapplication.util.ToastUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by codeest on 16/8/15.
 */

public class  WelcomePresenter extends RxPresenter<WelcomeContract.View> implements WelcomeContract.Presenter{

    private static final String RES = "1080*1776";

    private static final int COUNT_DOWN_TIME = 2200;

    private DataManager mDataManager;

    @Inject
    public WelcomePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getWelcomeData() {
        if(mDataManager == null){
            ToastUtil.show("mDataManager 为空");
            return;
        }
        addSubscribe(mDataManager.fetchDailyListInfo()
                .compose(RxUtil.<DailyListBean>rxSchedulerHelper())
                .map(new Function<DailyListBean, DailyListBean>() {
                    @Override
                    public DailyListBean apply(DailyListBean dailyListBean) {
                        Log.e("DataManager","网络返回数据："+dailyListBean.getStories().get(0).getTitle());
                        List<DailyListBean.StoriesBean> list = dailyListBean.getStories();
                        for(DailyListBean.StoriesBean item : list) {

                        }
                        return dailyListBean;
                    }
                })
                .subscribe()
        );
    }

    private void startCountDown() {
        addSubscribe(Flowable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                        mView.jumpToMain();
                    }
                })
        );
    }
}
