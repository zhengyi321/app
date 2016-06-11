package com.gototongcheng.Presenter;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.gototongcheng.view.activity.MainActivity;

/**
 * Created by zhyan on 16/6/10.
 */
public class MainActivityPresenter {

    private int fragmentContent;
    private FragmentActivity mActivity;
    public MainActivityPresenter(MainActivity activity, int RId){
        this.fragmentContent = RId;
        this.mActivity = activity;
    }
    public void showFragment(Fragment fragment){
        mActivity.getFragmentManager().beginTransaction().replace(fragmentContent, fragment).commit();
    }
}
