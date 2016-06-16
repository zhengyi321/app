package com.gototongcheng.Presenter;

import android.app.Activity;

import com.gototongcheng.application.R;
import com.gototongcheng.view.fragment.LoginCenterFragment;
import com.gototongcheng.view.fragment.MainShouYeFragment;

/**
 * Created by zhyan on 16/6/15.
 */
public class RegisterCenterFragmentPresenter extends BasePresenter{

    public RegisterCenterFragmentPresenter(){

    }
    public RegisterCenterFragmentPresenter(Activity activity){

        initViews(activity);
    }
    protected void initViews(Activity activity){
        this.activity = activity;
        mainActivityPresenter = new MainActivityPresenter(activity, R.id.fly_content);

    }
    public void back(){
        mainActivityPresenter.showFragment(new MainShouYeFragment(activity));
    }
    public void login(){
        mainActivityPresenter.showFragment(new LoginCenterFragment(activity));
    }
}
