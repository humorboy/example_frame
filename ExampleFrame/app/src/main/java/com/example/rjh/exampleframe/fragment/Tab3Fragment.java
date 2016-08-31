package com.example.rjh.exampleframe.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rjh.exampleframe.R;

/**
 * @author rjh
 * @Description:
 * @FileName:Tab1Fragment.java
 * @Package
 * @Date 2016/8/31 16:38
 */

public class Tab3Fragment extends BaseFragment {
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view  = inflater.inflate(R.layout.tab3_fragment,null);
		return view;
	}

}
