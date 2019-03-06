package com.humorboy.mvpapplication.di.component;

import android.app.Activity;

import com.humorboy.mvpapplication.di.module.ActivityModule;
import com.humorboy.mvpapplication.di.scope.ActivityScope;
import com.humorboy.mvpapplication.mvp.main.MainActivity;
import com.humorboy.mvpapplication.mvp.news.TestActivity;
import com.humorboy.mvpapplication.mvp.welcome.WelcomeActivity;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
    void inject(WelcomeActivity welcomeActivity);
    void inject(MainActivity mainActivity);
    void inject(TestActivity testActivity);
}
