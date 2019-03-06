package com.humorboy.mvpapplication.di.component;

import android.app.Activity;

import com.humorboy.mvpapplication.di.module.FragmentModule;
import com.humorboy.mvpapplication.di.scope.FragmentScope;
import com.humorboy.mvpapplication.mvp.main.fragment.DailyFragment;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();
    void inject(DailyFragment dailyFragment);
}
