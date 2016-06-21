package com.gototongcheng.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.TongChengRangCheckFragmentPresenter;
import com.gototongcheng.application.R;

import butterknife.OnClick;

/**
 * Created by admin on 16/6/15.
 */
public class TongChengRangCheckFragment extends BaseFragment {

    private CommonTopBarPresenter commonTopBarPresenter;
    private TongChengRangCheckFragmentPresenter tongChengRangCheckFragmentPresenter;
    public TongChengRangCheckFragment(){

    }
    @SuppressLint("ValidFragment")
    public TongChengRangCheckFragment(Activity activity){
        this.activity = activity;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_tongcheng_rang_check_sv;
    }

    @Override
    public void initViews() {
        commonTopBarPresenter = new CommonTopBarPresenter(activity);/*
        commonTopBarPresenter.topBarCommonWidget.rlyLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tongChengRangCheckFragmentPresenter.back();
            }
        });*/
        tongChengRangCheckFragmentPresenter = new TongChengRangCheckFragmentPresenter(activity);
    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("tongchengrangcheck");
    }

    @OnClick(R.id.rly_left)
    public void backOnclick(){
        tongChengRangCheckFragmentPresenter.back();
    }

    @OnClick(R.id.lly_common_area_select)
    public void areaSelectOnClick(){
        tongChengRangCheckFragmentPresenter.setArea();
    }

    @OnClick(R.id.rly_tongcheng_sending_rang_check_submit)
    public void rangCheckSubmit(){
        tongChengRangCheckFragmentPresenter.rangCheckSubmit();
    }

}
