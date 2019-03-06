package com.humorboy.mvpapplication.mvp.welcome.contract;

import com.humorboy.mvpapplication.base.BasePresenter;
import com.humorboy.mvpapplication.base.BaseView;
import com.humorboy.mvpapplication.mvp.welcome.model.WelcomeBean;

/**
 * Created by codeest on 16/8/15.
 */

public interface WelcomeContract {

    interface View extends BaseView {
        void showContent(WelcomeBean welcomeBean);
        void jumpToMain();
    }

    interface  Presenter extends BasePresenter<View> {
        void getWelcomeData();
    }
}
