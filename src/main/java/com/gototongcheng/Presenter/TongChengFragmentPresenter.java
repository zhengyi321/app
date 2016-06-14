package com.gototongcheng.Presenter;

import android.app.Activity;

import com.gototongcheng.application.R;
import com.gototongcheng.view.fragment.MainShouYeFragment;

/**
 * 同城快递 逻辑处理
 * Created by zhyan on 16/6/14.
 */
public class TongChengFragmentPresenter {

    private MainActivityPresenter mainActivityPresenter;
    private Activity activity;

    public TongChengFragmentPresenter(){

    }
    public TongChengFragmentPresenter(Activity activity){
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
