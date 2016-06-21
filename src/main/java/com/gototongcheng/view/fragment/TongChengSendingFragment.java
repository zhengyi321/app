package com.gototongcheng.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.MainActivityPresenter;
import com.gototongcheng.Presenter.TongChengSendingFragmentPresenter;
import com.gototongcheng.application.R;
import com.gototongcheng.cookies.MyIniClass;
import com.gototongcheng.model.TongChengSendingAddressModel;

import butterknife.OnClick;

/**
 * Created by zhyan on 16/6/16.
 */
public class TongChengSendingFragment extends BaseFragment {



    private CommonTopBarPresenter commonTopBarPresenter;
    private TongChengSendingFragmentPresenter tongChengSendingFragmentPresenter;

    public TongChengSendingFragment(){

    }
    @SuppressLint("ValidFragment")
    public TongChengSendingFragment(Activity activity){
        this.activity = activity;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_tongcheng_sending_sv;
    }

    @Override
    public void initViews() {
        commonTopBarPresenter = new CommonTopBarPresenter(activity);
        mainActivityPresenter = new MainActivityPresenter(activity,R.id.fly_content);
        tongChengSendingFragmentPresenter = new TongChengSendingFragmentPresenter(activity);


    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("tongchengsending");
    }

    @OnClick(R.id.rly_left)
    public void backOnclick(){
        tongChengSendingFragmentPresenter.back();
    }

    @OnClick(R.id.lly_tongcheng_sending_spay)
    public void spayOnclick(){
        tongChengSendingFragmentPresenter.radioManage(0);
    }
    @OnClick(R.id.lly_tongcheng_sending_rpay)
    public void rpayOnclick(){
        tongChengSendingFragmentPresenter.radioManage(1);
    }
    @OnClick(R.id.lly_tongcheng_sending_month_balance)
    public void monthBalanceOnclick(){
        tongChengSendingFragmentPresenter.radioManage(2);
    }
    @OnClick(R.id.lly_tongcheng_sending_recharge)
    public void rechargeOnclick(){
        tongChengSendingFragmentPresenter.radioManage(3);
    }
    @OnClick(R.id.lly_tongcheng_sending_time_to)
    public void timeToGetOnclick(){
        tongChengSendingFragmentPresenter.timeToGet();
    }
    @OnClick(R.id.lly_tongcheng_sending_sender)
    public void senderAddressOnclick(){
        mainActivityPresenter.showFragment(new TongChengSendingBuildAddressFragment(activity,"send"));
    }
    @OnClick(R.id.lly_tongcheng_sending_receiver)
    public void receiverAddressOnclick(){
        mainActivityPresenter.showFragment(new TongChengSendingBuildAddressFragment(activity,"receive"));
    }
    @OnClick(R.id.rly_tongcheng_sending_submit)
    public void dataSubmit(){
        Toast.makeText(getActivity(),"this is submit",Toast.LENGTH_SHORT).show();
        tongChengSendingFragmentPresenter.submit();
    }


}
