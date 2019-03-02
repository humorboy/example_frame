package com.humorboy.mvpapplication.main;

import android.Manifest;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.humorboy.mvpapplication.R;
import com.humorboy.mvpapplication.main.adapter.NewsAdapter;
import com.humorboy.mvpapplication.main.model.News;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements MainContract.View,View.OnClickListener {
    private MainPresenter presenter;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private List<News> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ...");
        progressDialog.setCancelable(false);

        recyclerView = findViewById(R.id.recyclerView );
        //设置适配器
        recyclerView.setAdapter(new NewsAdapter(data,getApplicationContext()));
        //构造 LinearLayputManager，并设置方向。LinearLayputManager效果类似ListView。
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置滑动方向：横向
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //添加Android自带的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);

        checkPermission();
        presenter = new MainPresenter(this);
        presenter.loadData();
    }

    private void checkPermission() {
        final RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .requestEach(Manifest.permission.CAMERA,
                        Manifest.permission.READ_PHONE_STATE)
                .subscribe(permission -> { // will emit 2 Permission objects
                    if (permission.granted) {
                        // `permission.name` is granted !
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // Denied permission without ask never again
                    } else {
                        // Denied permission with ask never again
                        // Need to go to the settings
                    }
                });
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
        }
    }

    @Override
    public void showData(List<News> data) {
        recyclerView.setAdapter(new NewsAdapter(data,getApplicationContext()));
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }
}
