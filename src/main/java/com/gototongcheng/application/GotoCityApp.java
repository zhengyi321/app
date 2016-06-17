package com.gototongcheng.application;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.gototongcheng.widget.commonstaticwidget.CommonStaticWidget;

/**
 * Created by zhyan on 16/6/11.
 */
public class GotoCityApp extends Application
{

    public static Context mAppContext;


    @Override
    public void onCreate()
    {

        super.onCreate();
        mAppContext = this;
        SDKInitializer.initialize(getApplicationContext());
    }


    public static Context getContext()
    {

        return mAppContext;
    }
}