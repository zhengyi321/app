package com.gototongcheng.application;

import android.app.Application;
import android.content.Context;

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
    }


    public static Context getContext()
    {

        return mAppContext;
    }
}