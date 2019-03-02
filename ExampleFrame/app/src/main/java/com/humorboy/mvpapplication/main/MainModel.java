package com.humorboy.mvpapplication.main;

import android.os.Handler;

//数据层 网络或者数据库获取data
public class MainModel {
   public static void getData(final String params, final MainCallback callback){
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
             switch (params){
                 case "success":
                     callback.onSuccess("根据参数"+params+"的请求网络数据成功");
                     break;
                 case "failure":
                     callback.onFailure("请求失败：参数有误");
                     break;
                 case "exception":
                     callback.onError();
                     break;
             }
               callback.onComplete();
           }
       },3000);

   }
}
