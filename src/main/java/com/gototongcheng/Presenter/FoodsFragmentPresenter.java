package com.gototongcheng.Presenter;

import android.app.Activity;

import com.gototongcheng.application.R;
import com.gototongcheng.view.fragment.MainShouYeFragment;

/**
 * 美食外送 逻辑处理
 * Created by zhyan on 16/6/14.
 */
public class FoodsFragmentPresenter extends BasePresenter{

    public FoodsFragmentPresenter(){

    }

    public FoodsFragmentPresenter(Activity activity){

        initViews(activity);
    }
    protected void initViews(Activity activity){
        this.activity = activity;
        mainActivityPresenter = new MainActivityPresenter(activity,R.id.fly_content);

    }
    public void back(){
        mainActivityPresenter.showFragment(new MainShouYeFragment(activity));
    }
}
