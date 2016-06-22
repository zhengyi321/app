package com.gototongcheng.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.FoodsFragmentPresenter;
import com.gototongcheng.application.R;

import butterknife.OnClick;

/**
 * Created by admin on 16/6/13.
 */
public class FoodsFragment extends BaseFragment {

    private CommonTopBarPresenter commonTopBarPresenter;
    private FoodsFragmentPresenter foodsFragmentPresenter;
    public FoodsFragment(){

    }
    @SuppressLint("ValidFragment")
    public FoodsFragment(Activity activity){
        this.activity = activity;
        commonTopBarPresenter = new CommonTopBarPresenter(activity);
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_shouye_foods_lly;
    }

    @Override
    public void initViews() {
        foodsFragmentPresenter = new FoodsFragmentPresenter(activity);

    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("foods");
    }
    @OnClick(R.id.rly_left_select)
    public void backOnClick(){
        foodsFragmentPresenter.back();
    }


}
