package com.humorboy.mvpapplication.mvp.welcome.present;

import com.humorboy.mvpapplication.base.RxPresenter;
import com.humorboy.mvpapplication.model.DataManager;
import com.humorboy.mvpapplication.mvp.welcome.contract.WelcomeContract;
import com.humorboy.mvpapplication.mvp.welcome.model.WelcomeBean;
import com.humorboy.mvpapplication.util.RxUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * Created by codeest on 16/8/15.
 */

public class  WelcomePresenter extends RxPresenter<WelcomeContract.View> implements WelcomeContract.Presenter{

    private static final String RES = "1080*1776";

    private static final int COUNT_DOWN_TIME = 3000;

    private DataManager mDataManager;

    @Inject
    public WelcomePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getWelcomeData() {
//        addSubscribe(mDataManager.fetchWelcomeInfo(RES)
//                .compose(RxUtil.<WelcomeBean>rxSchedulerHelper())
//                .subscribe(new Consumer<WelcomeBean>() {
//                    @Override
//                    public void accept(WelcomeBean welcomeBean) {
//                        LogUtil.e("WelcomeBean img = "+welcomeBean.getImg()+" text = "+welcomeBean.getText());
//                        mView.showContent(welcomeBean);
//                        startCountDown();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) {
//                        mView.jumpToMain();
//                    }
//                })
//        );

        WelcomeBean welcomeBean = new WelcomeBean("MvpDemo","http://pic1.win4000.com/wallpaper/3/57b175579a425.jpg");
        mView.showContent(welcomeBean);
        startCountDown();
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
