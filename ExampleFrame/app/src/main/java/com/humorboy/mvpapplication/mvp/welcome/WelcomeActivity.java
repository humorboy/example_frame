package com.humorboy.mvpapplication.mvp.welcome;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.humorboy.mvpapplication.R;
import com.humorboy.mvpapplication.app.Constants;
import com.humorboy.mvpapplication.base.BaseActivity;
import com.humorboy.mvpapplication.base.ImageLoader;
import com.humorboy.mvpapplication.mvp.welcome.contract.WelcomeContract;
import com.humorboy.mvpapplication.mvp.welcome.model.WelcomeBean;
import com.humorboy.mvpapplication.mvp.welcome.present.WelcomePresenter;
import com.humorboy.mvpapplication.util.LogUtil;

import butterknife.BindView;

/**
 * Created by humorboy on 2019/3/1.
 */
@Route(path = Constants.ACTIVITY_WELCOME_URL)
public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.View {

    @BindView(R.id.iv_welcome_bg)
    ImageView ivWelcomeBg;
    @BindView(R.id.tv_welcome_author)
    TextView tvWelcomeAuthor;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getWelcomeData();
    }

    @Override
    public void showContent(WelcomeBean welcomeBean) {
        LogUtil.e("WelcomeBean img = "+welcomeBean.getImg()+" text = "+welcomeBean.getText());
        ImageLoader.load(this, welcomeBean.getImg(), ivWelcomeBg);
        ivWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        tvWelcomeAuthor.setText(welcomeBean.getText());
    }

    @Override
    public void jumpToMain() {
        ARouter.getInstance().build(Constants.ACTIVITY_MAIN_URL).navigation();
    }

    @Override
    protected void onDestroy() {
        Glide.clear(ivWelcomeBg);
        super.onDestroy();
    }
}
