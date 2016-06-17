package com.gototongcheng.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.TongChengRangCheckFragmentPresenter;
import com.gototongcheng.application.R;

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
        commonTopBarPresenter = new CommonTopBarPresenter(activity);
        commonTopBarPresenter.topBarCommonWidget.rlyLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tongChengRangCheckFragmentPresenter.back();
            }
        });
        tongChengRangCheckFragmentPresenter = new TongChengRangCheckFragmentPresenter(activity);
    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("tongchengrangcheck");
    }


}
