package com.example.rjh.exampleframe.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.rjh.exampleframe.R;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author rjh
 * @Description:
 * @FileName:Tab1Fragment.java
 * @Package
 * @Date 2016/8/31 16:38
 */

public class Tab1Fragment extends BaseFragment implements View.OnClickListener {
	private View view;
	private Chronometer timer;
	private Timer timer1;
	private TextView textView;
	private TimerTask timerTask;
	private Button start_1,start_2,stop_1,stop_2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view  = inflater.inflate(R.layout.tab1_fragment,null);
		timer = (Chronometer) view.findViewById(R.id.timer);
		textView = (TextView) view.findViewById(R.id.text);
		start_1 = (Button) view.findViewById(R.id.start_1);
		start_1.setOnClickListener(this);
		start_2 = (Button) view.findViewById(R.id.start_2);
		start_2.setOnClickListener(this);
		stop_1 = (Button) view.findViewById(R.id.stop_1);
		stop_1.setOnClickListener(this);
		stop_2 = (Button) view.findViewById(R.id.stop_2);
		stop_2.setOnClickListener(this);
		timer1 = new Timer();
		return view;
	}


	private String getStringTime(int cnt) {
		int hour = cnt/3600;
		int min = cnt % 3600 / 60;
		int second = cnt % 60;
		return String.format(Locale.CHINA,"%02d:%02d:%02d",hour,min,second);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.start_1:
				timer.setBase(SystemClock.elapsedRealtime());//计时器清零
				int hour = (int) ((SystemClock.elapsedRealtime() - timer.getBase()) / 1000 / 60);
				timer.setFormat("0"+String.valueOf(hour)+":%s");
				timer.start();
				break;
			case R.id.stop_1:
				timer.stop();
				break;
			case R.id.start_2:
				timerTask = new TimerTask() {
					int cnt = 0;
					@Override
					public void run() {
						getActivity().runOnUiThread(new Runnable() {
							@Override
							public void run() {
								textView.setText(getStringTime(cnt++));
							}
						});
					}
				};
				timer1.schedule(timerTask,0,1000);
				break;
			case R.id.stop_2:
				if (!timerTask.cancel()){
					timerTask.cancel();
					timer1.cancel();
				}
				break;
			default:
				break;
		}
	}
}
