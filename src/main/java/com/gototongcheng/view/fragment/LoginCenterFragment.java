package com.gototongcheng.view.fragment;

import android.app.Activity;
import android.view.View;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.LoginCenterFragmentPresenter;
import com.gototongcheng.application.R;

/**
 * Created by zhyan on 16/6/15.
 */
public class LoginCenterFragment extends BaseFragment implements View.OnClickListener{


    private CommonTopBarPresenter commonTopBarPresenter;
    private Activity activity;
    private LoginCenterFragmentPresenter loginCenterFragmentPresenter;
    public LoginCenterFragment(){

    }

    public LoginCenterFragment(Activity activity){
        this.activity = activity;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_main_center_login_sv;
    }

    @Override
    public void initViews() {
        commonTopBarPresenter = new CommonTopBarPresenter(activity);
        commonTopBarPresenter.topBarCommonWidget.rlyLeft.setOnClickListener(this);
        commonTopBarPresenter.topBarCommonWidget.rlyRight.setOnClickListener(this);
        loginCenterFragmentPresenter = new LoginCenterFragmentPresenter(activity);
        loginCenterFragmentPresenter.widget.llyRememberLogin.setOnClickListener(this);
    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("login");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rly_left:
                loginCenterFragmentPresenter.back();
                break;
            case R.id.rly_right:
                loginCenterFragmentPresenter.register();
                break;
            case R.id.lly_remember_login:
                if(loginCenterFragmentPresenter.widget.cbRememberLogin.isChecked()){
                    loginCenterFragmentPresenter.widget.cbRememberLogin.setChecked(false);
                }else{
                    loginCenterFragmentPresenter.widget.cbRememberLogin.setChecked(true);
                }
                break;
        }
    }
}
