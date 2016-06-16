package com.gototongcheng.Presenter;

import android.app.Activity;
import android.widget.LinearLayout;

import com.gototongcheng.application.R;
import com.gototongcheng.view.fragment.MainShouYeFragment;
import com.gototongcheng.view.fragment.TongChengFeeCheckFragment;
import com.gototongcheng.view.fragment.TongChengGoodsTrackingFragment;
import com.gototongcheng.view.fragment.TongChengRangCheckFragment;
import com.gototongcheng.view.fragment.TongChengSendingFragment;
import com.gototongcheng.view.fragment.TongChengSiteCheckFragment;
import com.gototongcheng.view.fragment.TongChengTimeCheckFragment;

/**
 * 同城快递 逻辑处理
 * Created by zhyan on 16/6/14.
 */
public class TongChengFragmentPresenter extends BasePresenter{

    public  TongChengFragmentWidget widget;

    public TongChengFragmentPresenter(){

    }
    public TongChengFragmentPresenter(Activity activity){

        initViews(activity);
    }


    public void initViews(Activity activity) {
        this.activity = activity;
        mainActivityPresenter = new MainActivityPresenter(activity, R.id.fly_content);
        if(widget == null) {
            widget = new TongChengFragmentWidget();
        }
        widget.llyTongchengSending = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_sending);
        widget.llyTongchengGoodsTracking = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_goods_tracking);

        widget.llyTongchengFeeCheck = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_fee_check);
        widget.llyTongchengTimeCheck = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_time_check);

        widget.llyTongchengSiteCheck = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_site_check);
        widget.llyTongchengRangCheck = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_rang_check);
    }

    public void page(String type){
        switch (type){
            case "tongchengsending":
                mainActivityPresenter.showFragment(new TongChengSendingFragment(activity));
                break;
            case "tongchenggoodstracking":
                mainActivityPresenter.showFragment(new TongChengGoodsTrackingFragment(activity));
                break;
            case "tongchengfeecheck":
                mainActivityPresenter.showFragment(new TongChengFeeCheckFragment(activity));
                break;
            case "tongchengtimecheck":
                mainActivityPresenter.showFragment(new TongChengTimeCheckFragment(activity));
                break;
            case "tongchengsitecheck":
                mainActivityPresenter.showFragment(new TongChengSiteCheckFragment(activity));
                break;
            case "tongchengrangcheck":
                mainActivityPresenter.showFragment(new TongChengRangCheckFragment(activity));
                break;
        }
    }

    public void back(){
        mainActivityPresenter.showFragment(new MainShouYeFragment(activity));
    }

    public  class TongChengFragmentWidget{
        public LinearLayout llyTongchengSending;
        public LinearLayout llyTongchengGoodsTracking;

        public LinearLayout llyTongchengFeeCheck;
        public LinearLayout llyTongchengTimeCheck;

        public LinearLayout llyTongchengSiteCheck;
        public LinearLayout llyTongchengRangCheck;
    }
}
