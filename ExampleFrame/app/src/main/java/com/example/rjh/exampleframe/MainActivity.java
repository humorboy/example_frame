package com.example.rjh.exampleframe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rjh.exampleframe.activity.BaseActivity;
import com.example.rjh.exampleframe.activity.FullscreenActivity;
import com.example.rjh.exampleframe.fragment.Tab1Fragment;
import com.example.rjh.exampleframe.fragment.Tab2Fragment;
import com.example.rjh.exampleframe.fragment.Tab3Fragment;
import com.example.rjh.exampleframe.fragment.Tab4Fragment;
import com.example.rjh.exampleframe.ui.InterfaceTest;
import com.example.rjh.exampleframe.utils.Tasktimer;
import com.example.rjh.exampleframe.view.PagerSlidingTabStrip;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

/**
 * @author 任建红
 * @Description: 仅仅是一个自己锻炼的框架
 * @FileName:MainActivity.java
 * @Package
 * @Date 2016/9/5 17:03
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private PagerSlidingTabStrip tabs;//顶部切换tab
    private ViewPager pager;
    private DisplayMetrics dm;  // 获取当前屏幕的密度
    private String[] titles = {"Tab1", "Tab2","Tab3", "Tab4"};
    private static TabListAdapter pageAdapter;
    private Tab1Fragment tab1Fragment;//切换的fragment1
    private Tab2Fragment tab2Fragment;//切换的fragment2
    private Tab3Fragment tab3Fragment;//切换的fragment3
    private Tab4Fragment tab4Fragment;//切换的fragment4

    //用户中心
    private RelativeLayout mUserCenterLayout,mUserContentLayout,mUserTitleLayout;
    private TextView mUserTv;
    private int userTvHeight,mUserContentHeight,mUserCenterHeight;
    private Boolean mUserInfoShow = true;//用户中心是否显示
    private Tasktimer signInTime;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
    * @method  View 初始化
    */
    private void initView(){
        //初始化工具栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("主界面");

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        EditText tv = new EditText(this);
//        tv.setText("好好学习天天向上");
//        tv.setBackgroundColor(Color.RED);
//        actionBar.setCustomView(tv);

        //初始化侧滑栏
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //用户中心初始化
        initUserCenter();

        // tab 切换页面
        dm = getResources().getDisplayMetrics();
        pageAdapter=new TabListAdapter(getSupportFragmentManager());
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(pageAdapter);
        tabs.setViewPager(pager);
        setTabsValue();

        mHandler =  new Handler(getMainLooper());

        //初始化计时器控件
        signInTime = (Tasktimer) findViewById(R.id.signin_time_textview);
        signInTime.initTime(0, 3600 * 24);
        signInTime.start();
        signInTime.setOnTimeCompleteListener(new Tasktimer.OnTimeCompleteListener() {
            @Override
            public void onTimeComplete() {
                signInTime.setTextColor(Color.parseColor("#FF0000"));
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
    * @method 用户中心初始化
    */
    private void initUserCenter() {
        mUserCenterLayout = (RelativeLayout) findViewById(R.id.user_center_layout);
        mUserContentLayout = (RelativeLayout) findViewById(R.id.user_content);
        mUserTitleLayout = (RelativeLayout) findViewById(R.id.user_title_layout);
        mUserTv = (TextView) findViewById(R.id.user);
        //计算布局高度
        mUserCenterLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
               mUserCenterHeight = mUserCenterLayout.getHeight();
               userTvHeight = mUserTitleLayout.getHeight();
               mUserContentHeight = mUserContentLayout.getHeight();
               Log.d("TAG","控件高度："+" userTvHeight"+userTvHeight+" mUserContentHeight"+mUserContentHeight+" mUserCenterHeight"+mUserCenterHeight);
               ViewHelper.setTranslationY(mUserCenterLayout,mUserContentHeight);
            }
        });

        //用户中心点击事件
        mUserTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUserInfoShow){
                    showAnimation();//用户中心显示
                }else {
                    hidAnimation();//用户中心隐藏
                }
            }
        });
    }

    /**
    * @method 对PagerSlidingTabStrip的各项属性进行赋值
    */
    private void setTabsValue() {
        // 设置Tab是自动填充满屏幕的
        tabs.setShouldExpand(true);
        // 设置Tab的分割线是透明的
        tabs.setDividerColor(Color.TRANSPARENT);
        // 设置Tab底部线的高度
        tabs.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        // 设置Tab Indicator的高度
        tabs.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, dm));
        // 设置Tab标题文字的大小
        tabs.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, dm));
        // 设置Tab Indicator的颜色    馅饼的颜色   橘黄色
        tabs.setIndicatorColor(Color.parseColor("#EE1E9F77"));
        // 设置选中Tab文字的颜色 (这是我自定义的一个方法)
        tabs.setSelectedTextColor(Color.parseColor("#EE1E9F77"));
        // 取消点击Tab时的背景色
        tabs.setTabBackground(0);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
    * @method 创建菜单
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
    * @method 侧滑菜单点击事件
    */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
                new InterfaceTest() {
                    @Override
                    public void printLog(String msg) {
                        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                    }
                };
        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(this, FullscreenActivity.class));
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
    * @method 用户中心隐藏动画
    */
    private void hidAnimation() {
        mUserInfoShow = true;
        ViewPropertyAnimator.animate(mUserCenterLayout).translationYBy(mUserContentHeight)
                .setDuration(300)
                .start();
    }

    /**
    * @method 用户中心显示动画
    */
    private void showAnimation() {
        mUserInfoShow = false;
        ViewPropertyAnimator.animate(mUserCenterLayout).translationYBy(-mUserContentHeight)
                .setDuration(300)
                .start();
    }


    /**
    * @method fragmentPager 适配器
    */
    public class TabListAdapter extends FragmentPagerAdapter {

        public TabListAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                //tab1Fragment
                case 0:
                    if (tab1Fragment == null) {
                        tab1Fragment = new Tab1Fragment();
                    }
                    return tab1Fragment;
                //tab2Fragment
                case 1:
                    if (tab2Fragment == null) {
                        tab2Fragment = new Tab2Fragment();
                    }
                    return tab2Fragment;
                //tab3Fragment
                case 2:
                    if (tab3Fragment == null) {
                        tab3Fragment = new Tab3Fragment();
                    }
                    return tab3Fragment;
                //tab4Fragment
                case 3:
                    if (tab4Fragment == null) {
                        tab4Fragment = new Tab4Fragment();
                    }
                    return tab4Fragment;
                default:
                    return null;
            }
        }

    }

}
