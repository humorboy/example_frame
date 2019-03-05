package com.humorboy.mvpapplication.test.contract;


import com.humorboy.mvpapplication.base.BasePresenter;
import com.humorboy.mvpapplication.base.BaseView;
import com.humorboy.mvpapplication.test.model.DailyBeforeListBean;
import com.humorboy.mvpapplication.test.model.DailyListBean;

/**
 * Created by codeest on 16/8/11.
 */

public interface DailyContract {

    interface View extends BaseView {

        void showContent(DailyListBean info);

        void showMoreContent(String date, DailyBeforeListBean info);

        void doInterval(int currentCount);
    }

    interface Presenter extends BasePresenter<View> {

        void getDailyData();

        void getBeforeData(String date);

        void startInterval();

        void stopInterval();

        void insertReadToDB(int id);
    }
}
