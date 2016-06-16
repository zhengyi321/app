package com.gototongcheng.Presenter;

import android.app.Activity;

import com.gototongcheng.application.R;
import com.gototongcheng.view.fragment.TongChengFragment;

/**
 * Created by zhyan on 16/6/16.
 */
public class TongChengGoodsTrackingFragmentPresenter extends BasePresenter {

    public TongChengGoodsTrackingFragmentPresenter(){

    }
    public TongChengGoodsTrackingFragmentPresenter(Activity activity){
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
