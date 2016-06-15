package com.gototongcheng.Presenter;

import android.app.Activity;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.gototongcheng.application.R;
import com.gototongcheng.view.fragment.LoginCenterFragment;
import com.gototongcheng.view.fragment.MainShouYeFragment;
import com.gototongcheng.view.fragment.RegisterCenterFragment;

/**
 * Created by admin on 16/6/15.
 */
public class LoginCenterFragmentPresenter {


    private Activity activity;
    private MainActivityPresenter mainActivityPresenter;
    public LoginCenterFragmentWidget widget;
    public LoginCenterFragmentPresenter(){

    }
    public LoginCenterFragmentPresenter(Activity activity){
        this.activity = activity;
        initView(activity);
    }
    private void initView(Activity activity){
        widget = new LoginCenterFragmentWidget();
        mainActivityPresenter = new MainActivityPresenter(activity, R.id.fly_content);
        widget.llyRememberLogin = (LinearLayout)activity.findViewById(R.id.lly_remember_login);
        widget.cbRememberLogin = (CheckBox)activity.findViewById(R.id.cb_remember_login);
    }
    public void back(){
        mainActivityPresenter.showFragment(new MainShouYeFragment(activity));
    }
    public void register(){
        mainActivityPresenter.showFragment(new RegisterCenterFragment(activity));
    }


    public class LoginCenterFragmentWidget{
        public LinearLayout llyRememberLogin;
        public CheckBox cbRememberLogin;
    }
}
