package com.gototongcheng.Presenter;

import android.app.Activity;
import android.app.Fragment;

/**
 * Created by zhyan on 16/6/10.
 */
public class MainActivityPresenter extends BasePresenter{

    private int fragmentContent;
    private CommonBottomBarPresenter commonBottomBarPresenter;
    public MainActivityPresenter(Activity activity, int RId){
        this.fragmentContent = RId;
        this.activity = activity;
        initViews(activity);
    }

    protected void initViews(Activity activity){
        commonBottomBarPresenter = new CommonBottomBarPresenter(activity);
//        commonBottomBarPresenter.initBottom();
    }



    public void showFragment(Fragment fragment){
        activity.getFragmentManager().beginTransaction().replace(fragmentContent, fragment).commit();
    }
}
