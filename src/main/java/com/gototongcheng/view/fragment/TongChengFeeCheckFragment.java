package com.gototongcheng.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.TongChengFeeCheckFragmentPresenter;
import com.gototongcheng.application.R;

import butterknife.OnClick;

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


        tongChengFeeCheckPresenter = new TongChengFeeCheckFragmentPresenter(activity);
    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("tongchengfeecheck");
    }
    @OnClick(R.id.rly_left)
    public void backOnclick(){
        tongChengFeeCheckPresenter.back();
    }

    @OnClick(R.id.rly_tongcheng_sending_fee_check_submit)
    public void feeCheckOnclick(){
        tongChengFeeCheckPresenter.checkSubmit();
    }
    @OnClick(R.id.lly_tongcheng_fee_check_receive_address)
    public void receiveLocOnclick(){
        tongChengFeeCheckPresenter.receiveLoc();
    }

    @OnClick(R.id.lly_tongcheng_fee_check_send_address)
    public void sendLocOnclick(){
        tongChengFeeCheckPresenter.sendLoc();
    }
}
