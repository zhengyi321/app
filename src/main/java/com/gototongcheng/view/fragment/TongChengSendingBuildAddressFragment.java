package com.gototongcheng.view.fragment;

import android.app.Activity;
import android.view.View;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.TongChengRangCheckFragmentPresenter;
import com.gototongcheng.Presenter.TongChengSendingBuildAddressFragmentPresenter;
import com.gototongcheng.application.R;

/**
 * 新建收寄件地址
 * Created by zhyan on 16/6/16.
 */
public class TongChengSendingBuildAddressFragment extends BaseFragment {

    private int type;//0为收件地址 1为寄件地址
    private CommonTopBarPresenter commonTopBarPresenter;
    private TongChengSendingBuildAddressFragmentPresenter tongChengSendingBuildAddressFragmentPresenter;
    public TongChengSendingBuildAddressFragment(){

    }
    public TongChengSendingBuildAddressFragment(Activity activity,String type){
        this.activity = activity;
        switch (type){
            case "receive":
                this.type = 0;
                break;
            case "send":
                this.type = 1;
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_tongcheng_sending_build_address_sv;
    }

    @Override
    public void initViews() {
        commonTopBarPresenter = new CommonTopBarPresenter(activity);
        commonTopBarPresenter.topBarCommonWidget.rlyLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tongChengSendingBuildAddressFragmentPresenter.back();
            }
        });
        tongChengSendingBuildAddressFragmentPresenter = new TongChengSendingBuildAddressFragmentPresenter(activity);
    }

    @Override
    public void initToolBar() {
        switch (type){
            case 0:
                commonTopBarPresenter.initTopBar("tongchengsendingbuildreceiveaddress");
            break;
            case 1:
                commonTopBarPresenter.initTopBar("tongchengsendingbuildsendaddress");
                break;
        }
    }



}
