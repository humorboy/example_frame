package com.humorboy.mvpapplication.main;

import com.humorboy.mvpapplication.main.model.News;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;

    public MainPresenter(MainContract.View mainView) {
        this.mView = mainView;
    }

    @Override
    public void loadData() {
        List<News> data = new ArrayList<>();
        for (int i= 0;i < 50 ;i ++){
            data.add(new News("海峡新干线","第"+i+"条新闻"));
        }
        mView.showData(data);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
