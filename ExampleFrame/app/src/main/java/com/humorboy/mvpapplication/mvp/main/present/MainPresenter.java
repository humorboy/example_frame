package com.humorboy.mvpapplication.mvp.main.present;

import android.Manifest;

import com.humorboy.mvpapplication.base.RxPresenter;
import com.humorboy.mvpapplication.model.DataManager;
import com.humorboy.mvpapplication.mvp.main.contract.MainContract;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
/**
 * Created by codeest on 16/8/9.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter{

    private DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {

    }

    @Override
    public void checkVersion(final String currentVersion) {

    }

    @Override
    public void checkPermissions(RxPermissions rxPermissions) {
        addSubscribe(rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) {
                        if (granted) {
                            mView.showContent("获取写入权限成功");
                        } else {
                            mView.showErrorMsg("获取写入权限成功失败");
                        }
                    }
                })
        );
    }

}
