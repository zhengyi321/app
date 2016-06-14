package com.gototongcheng.Presenter;

import android.app.Activity;

import com.gototongcheng.application.R;
import com.gototongcheng.view.fragment.HoursShopFragment;
import com.gototongcheng.view.fragment.MainShouYeFragment;

/**
 * Created by zhyan on 16/6/14.
 */
public class HoursFragmentPresenter {
    private MainActivityPresenter mainActivityPresenter;
    private Activity activity;
    public HoursFragmentPresenter(){

    }
    public HoursFragmentPresenter(Activity activity){
        initViews(activity);
    }
    private void initViews(Activity activity){
        this.activity = activity;
        mainActivityPresenter = new MainActivityPresenter(activity, R.id.fly_content);

    }
    public void back(){
        mainActivityPresenter.showFragment(new MainShouYeFragment(activity));
    }
}
