package com.gototongcheng.Presenter;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gototongcheng.application.R;
import com.gototongcheng.view.fragment.TongChengFragment;
import com.gototongcheng.widget.popupwindow.CommonPopupWindow;

/**
 * Created by zhyan on 16/6/16.
 */
public class TongChengSendingFragmentPresenter extends BasePresenter implements View.OnClickListener{


    public TongChengSendingWidget tongChengSendingWidget ;
    public CommonPopupWindow commonPopupWindow;
    public TongChengSendingFragmentPresenter(){

    }
    public TongChengSendingFragmentPresenter(Activity activity){

        initViews(activity);
    }

    protected void initViews(Activity activity) {
        this.activity = activity;
        mainActivityPresenter = new MainActivityPresenter(activity, R.id.fly_content);
        commonPopupWindow = new CommonPopupWindow();
        if(tongChengSendingWidget == null) {
            tongChengSendingWidget = new TongChengSendingWidget();
        }

        tongChengSendingWidget.llyTongChengSendingTotal = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_sending_total);
        tongChengSendingWidget.llyTongchengSendingSender = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_sending_sender);
        tongChengSendingWidget.llyTongchengSendingReceiver = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_sending_receiver);


        tongChengSendingWidget.rgTongChengSendingPayMethod = (RadioGroup)activity.findViewById(R.id.rg_tongcheng_sending_paymethod) ;
        tongChengSendingWidget.llyTongChengSendingSpay = (LinearLayout) activity.findViewById(R.id.lly_tongcheng_sending_spay);
        tongChengSendingWidget.tvTongChengSendingSpay = (TextView)activity.findViewById(R.id.tv_tongcheng_sending_spay);
        tongChengSendingWidget.rbTongChengSendingSpay = (RadioButton)activity.findViewById(R.id.rb_tongcheng_sending_spay);
        tongChengSendingWidget.llyTongChengSendingSpay.setOnClickListener(this);

        tongChengSendingWidget.llyTongChengSendingRpay = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_sending_rpay);
        tongChengSendingWidget.tvTongChengSendingRpay = (TextView)activity.findViewById(R.id.tv_tongcheng_sending_rpay);
        tongChengSendingWidget.rbTongChengSendingRpay = (RadioButton)activity.findViewById(R.id.rb_tongcheng_sending_rpay);
        tongChengSendingWidget.llyTongChengSendingRpay.setOnClickListener(this);

        tongChengSendingWidget.llyTongChengSendingMonthBalance = (LinearLayout) activity.findViewById(R.id.lly_tongcheng_sending_month_balance);
        tongChengSendingWidget.tvTongChengSendingMonthBalance = (TextView)activity.findViewById(R.id.tv_tongcheng_sending_month_balance);
        tongChengSendingWidget.rbTongChengSendingMonthBalance = (RadioButton)activity.findViewById(R.id.rb_tongcheng_sending_month_balance);
        tongChengSendingWidget.llyTongChengSendingMonthBalance.setOnClickListener(this);

        tongChengSendingWidget.llyTongChengSendingRecharge = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_sending_recharge);
        tongChengSendingWidget.tvTongChengSendingRecharge = (TextView)activity.findViewById(R.id.tv_tongcheng_sending_recharge);
        tongChengSendingWidget.rbTongChengSendingRecharge = (RadioButton)activity.findViewById(R.id.rb_tongcheng_sending_recharge);
        tongChengSendingWidget.llyTongChengSendingRecharge.setOnClickListener(this);


        tongChengSendingWidget.tvTongChengSendingTimeTo = (TextView)activity.findViewById(R.id.tv_tongcheng_sending_time_to);
        tongChengSendingWidget.llyTongChengSendingTimeTo = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_sending_time_to);
        tongChengSendingWidget.llyTongChengSendingTimeTo.setOnClickListener(this);
    }



    public void back() {
        mainActivityPresenter.showFragment(new TongChengFragment(activity));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lly_tongcheng_sending_spay:
                radioManage(0);
                break;
            case R.id.lly_tongcheng_sending_rpay:
                radioManage(1);
                break;

            case R.id.lly_tongcheng_sending_month_balance:
                radioManage(2);
                break;

            case R.id.lly_tongcheng_sending_recharge:
                radioManage(3);
                break;
            case R.id.lly_tongcheng_sending_time_to:
                commonPopupWindow.DateTimeMinuteSecondPopup(R.id.tv_tongcheng_sending_time_to,activity,tongChengSendingWidget.llyTongChengSendingTotal,"上门时间");
                break;

        }
    }
    private void radioManage(int type){
        switch (type){
            case 0:

                tongChengSendingWidget.rbTongChengSendingSpay.setChecked(true);
                tongChengSendingWidget.rbTongChengSendingRpay.setChecked(false);
                tongChengSendingWidget.rbTongChengSendingMonthBalance.setChecked(false);
                tongChengSendingWidget.rbTongChengSendingRecharge.setChecked(false);
                tongChengSendingWidget.tvTongChengSendingSpay.setBackgroundResource(R.drawable.shape_yellow_radius);
                tongChengSendingWidget.tvTongChengSendingRpay.setBackgroundResource(R.drawable.shape_gray_radius);
                tongChengSendingWidget.tvTongChengSendingMonthBalance.setBackgroundResource(R.drawable.shape_gray_radius);
                tongChengSendingWidget.tvTongChengSendingRecharge.setBackgroundResource(R.drawable.shape_gray_radius);
                break;
            case 1:
                tongChengSendingWidget.rbTongChengSendingSpay.setChecked(false);
                tongChengSendingWidget.rbTongChengSendingRpay.setChecked(true);
                tongChengSendingWidget.rbTongChengSendingMonthBalance.setChecked(false);
                tongChengSendingWidget.rbTongChengSendingRecharge.setChecked(false);
                tongChengSendingWidget.tvTongChengSendingSpay.setBackgroundResource(R.drawable.shape_gray_radius);
                tongChengSendingWidget.tvTongChengSendingRpay.setBackgroundResource(R.drawable.shape_yellow_radius);
                tongChengSendingWidget.tvTongChengSendingMonthBalance.setBackgroundResource(R.drawable.shape_gray_radius);
                tongChengSendingWidget.tvTongChengSendingRecharge.setBackgroundResource(R.drawable.shape_gray_radius);
                break;

            case 2:
                tongChengSendingWidget.rbTongChengSendingSpay.setChecked(false);
                tongChengSendingWidget.rbTongChengSendingRpay.setChecked(false);
                tongChengSendingWidget.rbTongChengSendingMonthBalance.setChecked(true);
                tongChengSendingWidget.rbTongChengSendingRecharge.setChecked(false);
                tongChengSendingWidget.tvTongChengSendingSpay.setBackgroundResource(R.drawable.shape_gray_radius);
                tongChengSendingWidget.tvTongChengSendingRpay.setBackgroundResource(R.drawable.shape_gray_radius);
                tongChengSendingWidget.tvTongChengSendingMonthBalance.setBackgroundResource(R.drawable.shape_yellow_radius);
                tongChengSendingWidget.tvTongChengSendingRecharge.setBackgroundResource(R.drawable.shape_gray_radius);
                break;

            case 3:
                tongChengSendingWidget.rbTongChengSendingSpay.setChecked(false);
                tongChengSendingWidget.rbTongChengSendingRpay.setChecked(false);
                tongChengSendingWidget.rbTongChengSendingMonthBalance.setChecked(false);
                tongChengSendingWidget.rbTongChengSendingRecharge.setChecked(true);
                tongChengSendingWidget.tvTongChengSendingSpay.setBackgroundResource(R.drawable.shape_gray_radius);
                tongChengSendingWidget.tvTongChengSendingRpay.setBackgroundResource(R.drawable.shape_gray_radius);
                tongChengSendingWidget.tvTongChengSendingMonthBalance.setBackgroundResource(R.drawable.shape_gray_radius);
                tongChengSendingWidget.tvTongChengSendingRecharge.setBackgroundResource(R.drawable.shape_yellow_radius);
                break;
        }
    }
    public  class TongChengSendingWidget{

        public LinearLayout llyTongChengSendingTotal;

        public LinearLayout llyTongchengSendingSender;
        public LinearLayout llyTongchengSendingReceiver;


        //付款方式
        public LinearLayout llyTongChengSendingSpay;
        public LinearLayout llyTongChengSendingRpay;
        public LinearLayout llyTongChengSendingMonthBalance;
        public LinearLayout llyTongChengSendingRecharge;


        public TextView tvTongChengSendingSpay;
        public TextView tvTongChengSendingRpay;
        public TextView tvTongChengSendingMonthBalance;
        public TextView tvTongChengSendingRecharge;

        public RadioGroup rgTongChengSendingPayMethod;
        public RadioButton rbTongChengSendingSpay;
        public RadioButton rbTongChengSendingRpay;
        public RadioButton rbTongChengSendingMonthBalance;
        public RadioButton rbTongChengSendingRecharge;
        //付款方式

        //上门时间
        public LinearLayout llyTongChengSendingTimeTo;
        public TextView tvTongChengSendingTimeTo;

        //上门时间
    }

}
