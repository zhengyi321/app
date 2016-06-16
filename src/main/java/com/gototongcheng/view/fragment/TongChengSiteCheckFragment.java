package com.gototongcheng.view.fragment;

import android.app.Activity;
import android.view.View;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.TongChengSiteCheckFragmentPresenter;
import com.gototongcheng.application.R;

/**
 * Created by admin on 16/6/15.
 */
public class TongChengSiteCheckFragment extends BaseFragment {

    private CommonTopBarPresenter commonTopBarPresenter;
    private TongChengSiteCheckFragmentPresenter tongChengSiteCheckFragmentPresenter;
    public TongChengSiteCheckFragment(){

    }

    public TongChengSiteCheckFragment(Activity activity){
        this.activity = activity;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_tongcheng_site_check_sv;
    }

    @Override
    public void initViews() {
        commonTopBarPresenter = new CommonTopBarPresenter(activity);
        commonTopBarPresenter.topBarCommonWidget.rlyLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tongChengSiteCheckFragmentPresenter.back();
            }
        });
        tongChengSiteCheckFragmentPresenter = new TongChengSiteCheckFragmentPresenter(activity);
    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("tongchengsitecheck");
    }


}
