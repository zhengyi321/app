package com.gototongcheng.view.fragment;

import android.app.Activity;
import android.view.View;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.RegisterCenterFragmentPresenter;
import com.gototongcheng.application.R;

/**
 * Created by admin on 16/6/10.
 */
public class RegisterCenterFragment extends BaseFragment implements View.OnClickListener{

    private Activity activity;

    private CommonTopBarPresenter commonTopBarPresenter;
    private RegisterCenterFragmentPresenter registerCenterFragmentPresenter;
    public RegisterCenterFragment(){

    }
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
        commonTopBarPresenter.topBarCommonWidget.rlyLeft.setOnClickListener(this);
        commonTopBarPresenter.topBarCommonWidget.rlyRight.setOnClickListener(this);
        registerCenterFragmentPresenter = new RegisterCenterFragmentPresenter(activity);
    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("register");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rly_left:
                registerCenterFragmentPresenter.back();
                break;
            case R.id.rly_right:
                registerCenterFragmentPresenter.login();
                break;
        }
    }
}
