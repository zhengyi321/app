package com.gototongcheng.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.LoginCenterFragmentPresenter;
import com.gototongcheng.application.R;

import butterknife.OnClick;

/**
 * Created by zhyan on 16/6/15.
 */
public class LoginCenterFragment extends BaseFragment {


    private CommonTopBarPresenter commonTopBarPresenter;

    private LoginCenterFragmentPresenter loginCenterFragmentPresenter;
    public LoginCenterFragment(){

    }
    @SuppressLint("ValidFragment")
    public LoginCenterFragment(Activity activity){
        this.activity = activity;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_main_center_login_sv;
    }

    @Override
    public void initViews() {
        commonTopBarPresenter = new CommonTopBarPresenter(activity);/*
        commonTopBarPresenter.topBarCommonWidget.rlyLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCenterFragmentPresenter.back();
            }
        });
        commonTopBarPresenter.topBarCommonWidget.rlyRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCenterFragmentPresenter.register();
            }
        });*/
        loginCenterFragmentPresenter = new LoginCenterFragmentPresenter(activity);
        loginCenterFragmentPresenter.widget.llyRememberLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginCenterFragmentPresenter.widget.cbRememberLogin.isChecked()){
                    loginCenterFragmentPresenter.widget.cbRememberLogin.setChecked(false);
                }else{
                    loginCenterFragmentPresenter.widget.cbRememberLogin.setChecked(true);
                }
            }
        });
    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("login");
    }
    @OnClick(R.id.rly_left)
    public void backOnClick(){
        loginCenterFragmentPresenter.back();
    }

    @OnClick(R.id.rly_right)
    public void registerOnclick(){
        loginCenterFragmentPresenter.register();
    }

}
