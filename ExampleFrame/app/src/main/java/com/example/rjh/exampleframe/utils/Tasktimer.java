package com.example.rjh.exampleframe.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Chronometer;

import java.text.SimpleDateFormat;

/**
 * Created by renjianhong on 2016/3/2.
 */
public class Tasktimer extends Chronometer
{
    public Tasktimer(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        mTimeFormat = new SimpleDateFormat("hh:mm:ss");
        this.setOnChronometerTickListener(listener);
    }

    private long mTime;
    private long mNextTime;
    private OnTimeCompleteListener mListener;
    private SimpleDateFormat mTimeFormat;

    public Tasktimer(Context context)
    {
        super(context);

    }

    //重新启动计时
    public void reStart(long _time_s)
    {
        if (_time_s == -1)
        {
            mNextTime = mTime;
        }
        else
        {
            mTime = mNextTime = _time_s;
        }
        this.start();
    }

    public void reStart()
    {
        reStart(-1);
    }

    //不建议方法名用onResume()或onPause()，容易和activity生命周期混淆
    //继续计时
    public void onResume()
    {
        Log.d("TAGTAGTAG","Timer onResume");
        this.start();
    }

    //暂停计时
    public void onPause()
    {
        Log.d("TAGTAGTAG","Timer onPause");
        this.stop();
    }

    /**
     * 设置时间格式
     *
     * @param pattern 计时格式
     */
    public void setTimeFormat(String pattern)
    {
        mTimeFormat = new SimpleDateFormat(pattern);
    }

    public void setOnTimeCompleteListener(OnTimeCompleteListener l)
    {
        mListener = l;
    }

    OnChronometerTickListener listener = new OnChronometerTickListener()
    {
        @Override
        public void onChronometerTick(Chronometer chronometer)
        {
                if (mNextTime == mTime)
                {
//                    Tasktimer.this.stop();
                    if (null != mListener)
                        mListener.onTimeComplete();
//                return;
                }
            mNextTime++;
            updateTimeText();
        }
    };

    //初始化时间(秒)
    public void initTime(long time_start,long time_end)
    {
        mTime = time_end;
        mNextTime = time_start;
        updateTimeText();
    }

    //初始化时间（分秒）
    public void initTime(long _time_h,long _time_m,long _time_s) {
        initTime(_time_h*3600+_time_m * 60 + _time_s,_time_h*3600+_time_m * 60 + _time_s);
    }

    private void updateTimeText()
    {
        this.setText(FormatMiss(mNextTime));
    }

    // 将秒转化成小时分钟秒
    public String FormatMiss(long miss){
        String hh=miss/3600>9?miss/3600+"":"0"+miss/3600;
        String  mm=(miss % 3600)/60>9?(miss % 3600)/60+"":"0"+(miss % 3600)/60;
        String ss=(miss % 3600) % 60>9?(miss % 3600) % 60+"":"0"+(miss % 3600) % 60;
        return hh+":"+mm+":"+ss;
    }

    public interface OnTimeCompleteListener
    {
        void onTimeComplete();
    }

}
