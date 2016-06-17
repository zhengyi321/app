package com.gototongcheng.Presenter;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.gototongcheng.application.R;
import com.gototongcheng.view.fragment.TongChengFragment;
import com.gototongcheng.widget.popupwindow.CommonPopupWindow;

/**
 * Created by admin on 16/6/16.
 */
public class TongChengTimeCheckFragmentPresenter extends BasePresenter implements View.OnClickListener{


    private TongChengTimeCheckFragmentWidget tongChengTimeCheckFragmentWidget;
    private CommonBaiduMapPresenter commonBaiduMapPresenter;
    private CommonPopupWindow commonPopupWindow;
    public TongChengTimeCheckFragmentPresenter(){

    }
    public TongChengTimeCheckFragmentPresenter(Activity activity){
        initViews(activity);
    }

    @Override
    protected void initViews(Activity activity) {
        this.activity = activity;
        mainActivityPresenter = new MainActivityPresenter(activity, R.id.fly_content);
        if(tongChengTimeCheckFragmentWidget == null){
            tongChengTimeCheckFragmentWidget = new TongChengTimeCheckFragmentWidget();
        }
        commonBaiduMapPresenter = new CommonBaiduMapPresenter(activity);
        commonPopupWindow = new CommonPopupWindow();
        tongChengTimeCheckFragmentWidget.llyTongChengTimeCheckTotal = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_time_check_total);
        tongChengTimeCheckFragmentWidget.etTongChengTimeCheckSendTime = (EditText)activity.findViewById(R.id.et_tongcheng_time_check_send_time);
        tongChengTimeCheckFragmentWidget.llyTongChengTimeCheckSendTime = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_time_check_send_time);
        tongChengTimeCheckFragmentWidget.etTongChengTimeCheckReceiveAddress = (EditText)activity.findViewById(R.id.et_tongcheng_time_check_receive_address);
        tongChengTimeCheckFragmentWidget.llyTongChengTimeCheckReceiveAddress = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_time_check_receive_address);
        tongChengTimeCheckFragmentWidget.etTongChengTimeCheckSendAddress = (EditText)activity.findViewById(R.id.et_tongcheng_time_check_send_address);
        tongChengTimeCheckFragmentWidget.llyTongChengTimeCheckSendAddress = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_time_check_send_address);
        tongChengTimeCheckFragmentWidget.llyTongChengTimeCheckSendTime.setOnClickListener(this);
        tongChengTimeCheckFragmentWidget.llyTongChengTimeCheckSendAddress.setOnClickListener(this);
        tongChengTimeCheckFragmentWidget.llyTongChengTimeCheckReceiveAddress.setOnClickListener(this);


    }

    public void back(){
        mainActivityPresenter.showFragment(new TongChengFragment(activity));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lly_tongcheng_time_check_send_time:
                commonPopupWindow.DateTimeMinuteSecondPopup(R.id.et_tongcheng_time_check_send_time,activity,tongChengTimeCheckFragmentWidget.llyTongChengTimeCheckTotal,"寄件时间");
                break;
            case R.id.lly_tongcheng_time_check_send_address:
                commonBaiduMapPresenter.startLoc(tongChengTimeCheckFragmentWidget.etTongChengTimeCheckSendAddress);
                break;
            case R.id.lly_tongcheng_time_check_receive_address:
                commonBaiduMapPresenter.startLoc(tongChengTimeCheckFragmentWidget.etTongChengTimeCheckReceiveAddress);
                break;
        }
    }


    public class TongChengTimeCheckFragmentWidget{

        public LinearLayout llyTongChengTimeCheckTotal;

        public EditText etTongChengTimeCheckSendTime;
        public LinearLayout llyTongChengTimeCheckSendTime;

        public EditText etTongChengTimeCheckReceiveAddress;
        public LinearLayout llyTongChengTimeCheckReceiveAddress;

        public EditText etTongChengTimeCheckSendAddress;
        public LinearLayout llyTongChengTimeCheckSendAddress;




    }
}
