package com.gototongcheng.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.TongChengSiteCheckFragmentPresenter;
import com.gototongcheng.application.R;

import butterknife.OnClick;

/**
 * Created by admin on 16/6/15.
 */
public class TongChengSiteCheckFragment extends BaseFragment {

    private CommonTopBarPresenter commonTopBarPresenter;
    private TongChengSiteCheckFragmentPresenter tongChengSiteCheckFragmentPresenter;
    public TongChengSiteCheckFragment(){

    }
    @SuppressLint("ValidFragment")
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

        tongChengSiteCheckFragmentPresenter = new TongChengSiteCheckFragmentPresenter(activity);
    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("tongchengsitecheck");
    }

    @OnClick(R.id.rly_left)
    public void backOnclick(){
        tongChengSiteCheckFragmentPresenter.back();
    }

    @OnClick(R.id.lly_common_site_find_nearby)
    public void findNearByOnClick(){
        tongChengSiteCheckFragmentPresenter.findNearBy();
    }
    @OnClick(R.id.lly_common_area_select)
    public void setAreaOnClick(){
        tongChengSiteCheckFragmentPresenter.setArea();
    }
    @OnClick(R.id.rly_tongcheng_site_check_select_submit)
    public void selectSubmitOnclick(){
        tongChengSiteCheckFragmentPresenter.siteCheckSubmit();
    }
}
