package com.gototongcheng.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.WaterBeerFragmentPresenter;
import com.gototongcheng.application.R;

import butterknife.OnClick;

/**
 * Created by zhyan on 16/6/13.
 */
public class WaterBeerFragment extends BaseFragment {

    private WaterBeerFragmentPresenter waterBeerFragmentPresenter;
    private CommonTopBarPresenter commonTopBarPresenter;
    public WaterBeerFragment(){

    }
    @SuppressLint("ValidFragment")
    public WaterBeerFragment(Activity activity){
        this.activity = activity;
        commonTopBarPresenter = new CommonTopBarPresenter(activity);
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_shouye_waterbeer_lly;
    }

    @Override
    public void initViews() {
        waterBeerFragmentPresenter = new WaterBeerFragmentPresenter(getActivity());

    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("waterbeer");
    }
    @OnClick(R.id.rly_left_select)
    public void backOnclick(){
        waterBeerFragmentPresenter.back();
    }


}
