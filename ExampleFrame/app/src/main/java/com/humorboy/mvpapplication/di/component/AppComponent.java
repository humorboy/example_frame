package com.humorboy.mvpapplication.di.component;

import com.humorboy.mvpapplication.app.App;
import com.humorboy.mvpapplication.di.module.AppModule;
import com.humorboy.mvpapplication.di.module.HttpModule;
import com.humorboy.mvpapplication.http.RetrofitHelper;
import com.humorboy.mvpapplication.model.DataManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类

//    RealmHelper realmHelper();    //提供数据库帮助类
//
//    ImplPreferencesHelper preferencesHelper(); //提供sp帮助类
}
