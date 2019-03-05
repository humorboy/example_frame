package com.humorboy.mvpapplication.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.humorboy.mvpapplication.R;
import com.humorboy.mvpapplication.model.DataManager;
import com.humorboy.mvpapplication.test.model.DailyListBean;
import com.humorboy.mvpapplication.util.RxUtil;
import com.humorboy.mvpapplication.util.ToastUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class TestActivity extends Activity {
    @BindView(R.id.textview)
    TextView textview;

    @BindView(R.id.button)
    Button button;

    private DataManager mDataManager;
    protected CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDataManager == null){
                    ToastUtil.show("mDataManager 为空");
                    return;
                }
                addSubscribe(mDataManager.fetchDailyListInfo()
                        .compose(RxUtil.<DailyListBean>rxSchedulerHelper())
                        .map(new Function<DailyListBean, DailyListBean>() {
                            @Override
                            public DailyListBean apply(DailyListBean dailyListBean) {
                                Log.e("DataManager","网络返回数据："+dailyListBean.getStories().get(0).getTitle());
                                List<DailyListBean.StoriesBean> list = dailyListBean.getStories();
                                for(DailyListBean.StoriesBean item : list) {

                                }
                                return dailyListBean;
                            }
                        })
                        .subscribe()
                );
            }
        });
    }

    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }
}
