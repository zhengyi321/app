package com.gototongcheng.Presenter;

import android.app.Activity;

/**
 * Created by admin on 16/6/16.
 */
public abstract class BasePresenter {
    protected MainActivityPresenter mainActivityPresenter;
    protected Activity activity;
    protected abstract void initViews(Activity activity);
}
