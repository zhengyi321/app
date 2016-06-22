package com.gototongcheng.Presenter;

import android.app.Activity;
import android.content.Context;

/**
 * Created by admin on 16/6/16.
 */
public abstract class BasePresenter {
    protected MainActivityPresenter mainActivityPresenter;
    protected Activity activity;
    protected abstract void initViews(Activity activity);
}
