package com.example.rjh.exampleframe.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

/**
 * @author rjh
 * @Description:
 * @FileName:BaseFragment.java
 * @Package
 * @Date 2016/8/31 16:40
 */

public abstract class BaseFragment extends Fragment {

	protected LayoutInflater inflater;
	protected Activity activity;

	// 模式化窗口 用作遮罩层
	protected Dialog dialog;
	protected String hotelCode;
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
	}
	public BaseFragment() {
	}


	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
