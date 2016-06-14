package com.gototongcheng.Presenter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.gototongcheng.view.activity.MainActivity;

/**
 * Created by zhyan on 16/6/10.
 */
public class MainActivityPresenter {

    private int fragmentContent;
    private Activity activity;
    private CommonBottomBarPresenter commonBottomBarPresenter;
    public MainActivityPresenter(Activity activity, int RId){
        this.fragmentContent = RId;
        this.activity = activity;
        initViews(activity);
    }

    private void initViews(Activity activity){
        commonBottomBarPresenter = new CommonBottomBarPresenter(activity);
//        commonBottomBarPresenter.initBottom();
    }

    public void showFragment(Fragment fragment){
        activity.getFragmentManager().beginTransaction().replace(fragmentContent, fragment).commit();
    }
}
