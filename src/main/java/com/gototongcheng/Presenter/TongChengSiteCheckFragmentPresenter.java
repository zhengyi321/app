package com.gototongcheng.Presenter;

import android.app.Activity;

import com.gototongcheng.application.R;
import com.gototongcheng.view.fragment.TongChengFragment;

/**
 * Created by admin on 16/6/16.
 */
public class TongChengSiteCheckFragmentPresenter extends BasePresenter{

    public TongChengSiteCheckFragmentPresenter(){

    }
    public TongChengSiteCheckFragmentPresenter(Activity activity){
        initViews(activity);
    }

    @Override
    protected void initViews(Activity activity) {
        this.activity = activity;
        mainActivityPresenter = new MainActivityPresenter(activity, R.id.fly_content);
    }
    public void back(){
        mainActivityPresenter.showFragment(new TongChengFragment(activity));
    }
}
