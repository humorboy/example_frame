package com.humorboy.mvpapplication.mvp.main.contract;


import com.humorboy.mvpapplication.base.BasePresenter;
import com.humorboy.mvpapplication.base.BaseView;
import com.humorboy.mvpapplication.mvp.main.model.DailyBeforeListBean;
import com.humorboy.mvpapplication.mvp.main.model.DailyListBean;
import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * Created by codeest on 16/8/11.
 */

public interface MainContract {

    interface View extends BaseView {
        void showContent(String info);
    }

    interface Presenter extends BasePresenter<View> {

        void checkVersion(String currentVersion);

        void checkPermissions(RxPermissions rxPermissions);
    }
}
