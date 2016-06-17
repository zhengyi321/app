package com.gototongcheng.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.TongChengFeeCheckFragmentPresenter;
import com.gototongcheng.application.R;

/**
 * Created by zhyan on 16/6/16.
 */
public class TongChengFeeCheckFragment extends BaseFragment {


    private CommonTopBarPresenter commonTopBarPresenter;
    private TongChengFeeCheckFragmentPresenter tongChengFeeCheckPresenter;
    public TongChengFeeCheckFragment(){

    }
    @SuppressLint("ValidFragment")
    public TongChengFeeCheckFragment(Activity activity){
        this.activity = activity;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_tongcheng_fee_check_sv;
    }

    @Override
    public void initViews() {
        commonTopBarPresenter = new CommonTopBarPresenter(activity);

        commonTopBarPresenter.topBarCommonWidget.rlyLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tongChengFeeCheckPresenter.back();
            }
        });
        tongChengFeeCheckPresenter = new TongChengFeeCheckFragmentPresenter(activity);
    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("tongchengfeecheck");
    }


}
