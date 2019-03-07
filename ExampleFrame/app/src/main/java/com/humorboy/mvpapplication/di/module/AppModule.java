package com.humorboy.mvpapplication.di.module;

import com.humorboy.mvpapplication.app.App;
import com.humorboy.mvpapplication.http.HttpHelper;
import com.humorboy.mvpapplication.http.RetrofitHelper;
import com.humorboy.mvpapplication.model.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by humorboy on 19/3/1.
 */

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper) {
        return new DataManager(httpHelper);
    }
}
