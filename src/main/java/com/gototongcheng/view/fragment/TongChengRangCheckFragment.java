package com.gototongcheng.view.fragment;

import android.app.Activity;

import com.gototongcheng.application.R;

/**
 * Created by admin on 16/6/15.
 */
public class TongChengRangCheckFragment extends BaseFragment{
    private Activity activity;
    public TongChengRangCheckFragment(){

    }
    public TongChengRangCheckFragment(Activity activity){
        this.activity = activity;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_tongcheng_rang_check_sv;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initToolBar() {

    }
}
