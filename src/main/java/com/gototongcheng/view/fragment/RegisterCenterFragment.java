package com.gototongcheng.view.fragment;

import android.app.Activity;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.application.R;

/**
 * Created by admin on 16/6/10.
 */
public class RegisterCenterFragment extends BaseFragment{

    private Activity activity;

    private CommonTopBarPresenter commonTopBarPresenter;
    public RegisterCenterFragment(){

    }
    public RegisterCenterFragment(Activity activity){
        this.activity = activity;

        commonTopBarPresenter = new CommonTopBarPresenter(activity);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_center_register_lly;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("register");
    }
}
