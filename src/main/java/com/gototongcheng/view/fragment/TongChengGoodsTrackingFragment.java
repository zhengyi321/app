package com.gototongcheng.view.fragment;

import android.app.Activity;
import android.view.View;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.TongChengGoodsTrackingFragmentPresenter;
import com.gototongcheng.application.R;

/**
 * Created by zhyan on 16/6/16.
 */
public class TongChengGoodsTrackingFragment extends BaseFragment {


    private CommonTopBarPresenter commonTopBarPresenter;
    private TongChengGoodsTrackingFragmentPresenter tongChengGoodsTrackingPresenter;
    public TongChengGoodsTrackingFragment(){

    }

    public TongChengGoodsTrackingFragment(Activity activity){
        this.activity = activity;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_tongcheng_goods_tracking_sv;
    }

    @Override
    public void initViews() {
        commonTopBarPresenter = new CommonTopBarPresenter(activity);
        commonTopBarPresenter.topBarCommonWidget.rlyLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tongChengGoodsTrackingPresenter.back();
            }
        });
        tongChengGoodsTrackingPresenter = new TongChengGoodsTrackingFragmentPresenter(activity);
    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("tongchenggoodstracking");
    }


}
