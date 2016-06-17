package com.gototongcheng.Presenter;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.gototongcheng.application.R;
import com.gototongcheng.view.fragment.TongChengFragment;

/**
 * Created by admin on 16/6/16.
 */
public class TongChengFeeCheckFragmentPresenter extends BasePresenter implements View.OnClickListener{

    private TongChengFeeCheckWidget tongChengFeeCheckWidget;
    private CommonBaiduMapPresenter commonBaiduMapPresenter;
    public TongChengFeeCheckFragmentPresenter(){

    }
    public TongChengFeeCheckFragmentPresenter(Activity activity){
        initViews(activity);
    }

    @Override
    protected void initViews(Activity activity) {
        this.activity = activity;
        mainActivityPresenter = new MainActivityPresenter(activity, R.id.fly_content);
        commonBaiduMapPresenter = new CommonBaiduMapPresenter(activity);
        if(tongChengFeeCheckWidget == null) {
            tongChengFeeCheckWidget = new TongChengFeeCheckWidget();
        }
        tongChengFeeCheckWidget.etTongchengFeeCheckReceiveAddress = (EditText)activity.findViewById(R.id.et_tongcheng_fee_check_receive_address);
        tongChengFeeCheckWidget.etTongchengFeeCheckSendAddress = (EditText)activity.findViewById(R.id.et_tongcheng_fee_check_send_address);
        tongChengFeeCheckWidget.llyTongchengFeeCheckReceiveAddress = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_fee_check_receive_address);
        tongChengFeeCheckWidget.llyTongchengFeeCheckSendAddress = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_fee_check_send_address);
        tongChengFeeCheckWidget.llyTongchengFeeCheckReceiveAddress.setOnClickListener(this);
        tongChengFeeCheckWidget.llyTongchengFeeCheckSendAddress.setOnClickListener(this);
    }

    public void back(){
        mainActivityPresenter.showFragment(new TongChengFragment(activity));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lly_tongcheng_fee_check_receive_address:
                commonBaiduMapPresenter.startLoc(tongChengFeeCheckWidget.etTongchengFeeCheckReceiveAddress);
                break;
            case R.id.lly_tongcheng_fee_check_send_address:
                commonBaiduMapPresenter.startLoc(tongChengFeeCheckWidget.etTongchengFeeCheckSendAddress);
                break;
        }
    }

    public class TongChengFeeCheckWidget{
        public EditText etTongchengFeeCheckReceiveAddress;
        public LinearLayout llyTongchengFeeCheckReceiveAddress;

        public EditText etTongchengFeeCheckSendAddress;
        public LinearLayout llyTongchengFeeCheckSendAddress;
    }
}
