package com.gototongcheng.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.RegisterCenterFragmentPresenter;
import com.gototongcheng.application.R;

/**
 * Created by admin on 16/6/10.
 */
public class RegisterCenterFragment extends BaseFragment {



    private CommonTopBarPresenter commonTopBarPresenter;
    private RegisterCenterFragmentPresenter registerCenterFragmentPresenter;
    public RegisterCenterFragment(){

    }
    @SuppressLint("ValidFragment")
    public RegisterCenterFragment(Activity activity){
        this.activity = activity;


    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_main_center_register_sv;
    }

    @Override
    public void initViews() {
        commonTopBarPresenter = new CommonTopBarPresenter(activity);
        commonTopBarPresenter.topBarCommonWidget.rlyLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerCenterFragmentPresenter.back();
            }
        });
        commonTopBarPresenter.topBarCommonWidget.rlyRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerCenterFragmentPresenter.login();
            }
        });
        registerCenterFragmentPresenter = new RegisterCenterFragmentPresenter(activity);
    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("register");
    }


}
