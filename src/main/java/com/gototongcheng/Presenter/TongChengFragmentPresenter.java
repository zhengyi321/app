package com.gototongcheng.Presenter;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.gototongcheng.adapter.MainCircleViewPageAdapter;
import com.gototongcheng.application.R;
import com.gototongcheng.mapping.MainShouYeMapper;
import com.gototongcheng.mapping.rxjava.ApiCallback;
import com.gototongcheng.mapping.rxjava.SubscriberCallBack;
import com.gototongcheng.model.CommonCircleModel;
import com.gototongcheng.view.fragment.MainShouYeFragment;
import com.gototongcheng.view.fragment.TongChengFeeCheckFragment;
import com.gototongcheng.view.fragment.TongChengGoodsTrackingFragment;
import com.gototongcheng.view.fragment.TongChengRangCheckFragment;
import com.gototongcheng.view.fragment.TongChengSendingFragment;
import com.gototongcheng.view.fragment.TongChengSiteCheckFragment;
import com.gototongcheng.view.fragment.TongChengTimeCheckFragment;
import com.gototongcheng.widget.LinearLayout.CircleIndicator;
import com.gototongcheng.widget.progressview.CircleProgressView;

import java.util.Timer;
import java.util.TimerTask;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * 同城快递 逻辑处理
 * Created by zhyan on 16/6/14.
 */
public class TongChengFragmentPresenter extends BasePresenter{

    public  TongChengFragmentWidget tongChengWidget;


    private MainShouYeMapper mainShouYeMapper;
    private MainCircleViewPageAdapter mainCircleViewPageAdapter;
    private Timer mTimer;
    private int size;
    private int mPagerPosition = 0;
    private BannerTask mTimerTask;
    private boolean mIsUserTouched = false;

    public TongChengFragmentPresenter(){

    }
    public TongChengFragmentPresenter(Activity activity){

        initViews(activity);
    }


    public void initViews(Activity activity) {
        this.activity = activity;
        mainActivityPresenter = new MainActivityPresenter(activity, R.id.fly_content);
        if(tongChengWidget == null) {
            tongChengWidget = new TongChengFragmentWidget();
        }


        //轮播
        tongChengWidget.llyTongChengCircle = (CircleIndicator)activity.findViewById(R.id.lly_tongcheng_circle);
        tongChengWidget.vpTongChengCircle = (ViewPager)activity.findViewById(R.id.vp_tongcheng_circle);
        tongChengWidget.mCircleProgressView = (CircleProgressView)activity.findViewById(R.id.circle_progress);
        mainShouYeMapper = new MainShouYeMapper();
        getCircleViewFromNet();
        //轮播
        tongChengWidget.llyTongchengSending = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_sending);
        tongChengWidget.llyTongchengGoodsTracking = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_goods_tracking);

        tongChengWidget.llyTongchengFeeCheck = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_fee_check);
        tongChengWidget.llyTongchengTimeCheck = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_time_check);

        tongChengWidget.llyTongchengSiteCheck = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_site_check);
        tongChengWidget.llyTongchengRangCheck = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_rang_check);
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






    //从后台获取轮播页面
    private void getCircleViewFromNet(){
        mainShouYeMapper.getCirclePic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //  if (!isDownRefresh)
                        //  {
                        showProgress();
                        //  }
                    }
                })
                .subscribe(new SubscriberCallBack<CommonCircleModel>(new ApiCallback<CommonCircleModel>() {
                    @Override
                    public void onSuccess(CommonCircleModel model) {

                        initCircleViewPager(model);
                    }

                    @Override
                    public void onFailure(int code, String msg) {

                    }

                    @Override
                    public void onCompleted() {
                        hideProgress();
                    }
                }))
        ;
    }

    //轮播页面初始化
    public void initCircleViewPager(CommonCircleModel mainShouYeCircleModel){
        mainCircleViewPageAdapter = new MainCircleViewPageAdapter(activity,mainShouYeCircleModel);
        size = mainShouYeCircleModel.getData().size();
        tongChengWidget.vpTongChengCircle.setAdapter(mainCircleViewPageAdapter);
        tongChengWidget.llyTongChengCircle.setViewPager(tongChengWidget.vpTongChengCircle);
        startViewPagerRun();

    }

    public void circlePositionBegin(){
        mPagerPosition = 0;
    }
    public void startViewPagerRun()
    {
        //执行ViewPager进行轮播
        mTimer = new Timer();
        mTimerTask = new BannerTask();
        //   mTimer.schedule(mTimerTask, 5000, 0000);
        mTimer.schedule(mTimerTask, 3000,3000);
    }





    private class BannerTask extends TimerTask
    {

        @Override
        public void run()
        {

            if (!mIsUserTouched)
            {
                mPagerPosition = (mPagerPosition + 1) % size;
                if (activity != null)
                {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tongChengWidget.vpTongChengCircle.setCurrentItem(mPagerPosition);/*
                            if (mPagerPosition == size - 1)
                            {
                                mViewPager.setCurrentItem(size - 1, false);
                            } else
                            {
                                mViewPager.setCurrentItem(mPagerPosition);
                            }*/
                        }
                    });
                }
            }


        }
    }

    private void showProgress()
    {

        tongChengWidget.mCircleProgressView.setVisibility(View.VISIBLE);
        tongChengWidget.mCircleProgressView.spin();
        //      mRecyclerView.setVisibility(View.GONE);
    }

    public void hideProgress()
    {

        tongChengWidget.mCircleProgressView.setVisibility(View.GONE);
        tongChengWidget.mCircleProgressView.stopSpinning();
        //     mRecyclerView.setVisibility(View.VISIBLE);
    }
    public void onDestroy()
    {
        // TODO Auto-generated method stub
        if (mTimer != null)
        {
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask != null)
        {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }



    public  class TongChengFragmentWidget{
        public LinearLayout llyTongchengSending;
        public LinearLayout llyTongchengGoodsTracking;

        public LinearLayout llyTongchengFeeCheck;
        public LinearLayout llyTongchengTimeCheck;

        public LinearLayout llyTongchengSiteCheck;
        public LinearLayout llyTongchengRangCheck;

        public CircleIndicator llyTongChengCircle;
        public ViewPager vpTongChengCircle;
        public CircleProgressView mCircleProgressView;
    }
}
