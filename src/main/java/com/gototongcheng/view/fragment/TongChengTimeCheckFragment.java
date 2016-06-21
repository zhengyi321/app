package com.gototongcheng.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.TongChengFeeCheckFragmentPresenter;
import com.gototongcheng.Presenter.TongChengTimeCheckFragmentPresenter;
import com.gototongcheng.application.R;

import butterknife.OnClick;

/**
 * Created by admin on 16/6/15.
 */
public class TongChengTimeCheckFragment extends BaseFragment {


    private CommonTopBarPresenter commonTopBarPresenter;
    private TongChengTimeCheckFragmentPresenter tongChengTimeCheckFragmentPresenter;
    public TongChengTimeCheckFragment(){

    }
    @SuppressLint("ValidFragment")
    public TongChengTimeCheckFragment(Activity activity){
        this.activity = activity;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_tongcheng_time_check_sv;
    }

    @Override
    public void initViews() {
        commonTopBarPresenter = new CommonTopBarPresenter(activity);
        tongChengTimeCheckFragmentPresenter = new TongChengTimeCheckFragmentPresenter(activity);
    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("tongchengtimecheck");
    }
    @OnClick(R.id.rly_left)
    public void backOnclick(){
        tongChengTimeCheckFragmentPresenter.back();
    }
    @OnClick(R.id.lly_tongcheng_time_check_send_time)
    public void setTimeOnclick(){
        tongChengTimeCheckFragmentPresenter.setTime();
    }
    @OnClick(R.id.lly_tongcheng_time_check_send_address)
    public void sendAddressOnclick(){
        tongChengTimeCheckFragmentPresenter.sendLoc();
    }
    @OnClick(R.id.lly_tongcheng_time_check_receive_address)
    public void receiveAddressOnClick(){
        tongChengTimeCheckFragmentPresenter.receiveLoc();
    }
    @OnClick(R.id.rly_tongcheng_sending_time_check_submit)
    public void timeCheckSubmit(){
        tongChengTimeCheckFragmentPresenter.timeCheckSubmit();
    }
}
