package com.gototongcheng.Presenter;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.gototongcheng.adapter.MainCircleViewPageAdapter;
import com.gototongcheng.adapter.ShouYeHoursFirstGVAdapter;
import com.gototongcheng.adapter.ShouYeHoursSecondGVAdapter;
import com.gototongcheng.application.R;
import com.gototongcheng.mapping.ShouYeHoursMapper;
import com.gototongcheng.mapping.rxjava.ApiCallback;
import com.gototongcheng.mapping.rxjava.SubscriberCallBack;
import com.gototongcheng.model.CommonCircleModel;
import com.gototongcheng.view.fragment.MainShouYeFragment;
import com.gototongcheng.widget.LinearLayout.CircleIndicator;
import com.gototongcheng.widget.gridview.NoScroolGridView;
import com.gototongcheng.widget.progressview.CircleProgressView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by zhyan on 16/6/14.
 */
public class HoursFragmentPresenter extends BasePresenter{

    private HoursFragmentWidget hoursFragmentWidget;
    private List<String> dataList;
    private ShouYeHoursFirstGVAdapter shouYeHoursFirstGVAdapter;
    private ShouYeHoursSecondGVAdapter shouYeHoursSecondGVAdapter;
    private MainCircleViewPageAdapter mainCircleViewPageAdapter;
    private BannerTask mTimerTask;
    private boolean mIsUserTouched = false;
    private Timer mTimer;
    private int size;
    private int mPagerPosition = 0;
    private ShouYeHoursMapper shouYeHoursMapper;
    public HoursFragmentPresenter(){

    }
    public HoursFragmentPresenter(Activity activity){
        initViews(activity);
    }
    protected void initViews(Activity activity){
        this.activity = activity;
        mainActivityPresenter = new MainActivityPresenter(activity, R.id.fly_content);
        shouYeHoursMapper = new ShouYeHoursMapper();
        hoursFragmentWidget = new HoursFragmentWidget(activity);
        initDataList();
        getCircleViewFromNet();
        shouYeHoursFirstGVAdapter = new ShouYeHoursFirstGVAdapter(activity,dataList);
        shouYeHoursSecondGVAdapter = new ShouYeHoursSecondGVAdapter(activity,dataList);
        hoursFragmentWidget.gvShouyeHoursShopFirst.setAdapter(shouYeHoursFirstGVAdapter);
        hoursFragmentWidget.gvShouyeHoursShopSecond.setAdapter(shouYeHoursSecondGVAdapter);
    }
    private void initDataList(){
        dataList = new ArrayList<String>();
        dataList.add("zz");
        dataList.add("zz");
        dataList.add("zz");

    }

    public void back(){
        mainActivityPresenter.showFragment(new MainShouYeFragment(activity));
    }
    //从后台获取轮播页面
    private void getCircleViewFromNet(){
        shouYeHoursMapper.getCirclePic()
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
    private void showProgress()
    {

        hoursFragmentWidget.circleProgress.setVisibility(View.VISIBLE);
        hoursFragmentWidget.circleProgress.spin();
        //      mRecyclerView.setVisibility(View.GONE);
    }

    public void hideProgress()
    {

        hoursFragmentWidget.circleProgress.setVisibility(View.GONE);
        hoursFragmentWidget.circleProgress.stopSpinning();
        //     mRecyclerView.setVisibility(View.VISIBLE);
    }
    //轮播页面初始化
    public void initCircleViewPager(CommonCircleModel mainShouYeCircleModel){
        mainCircleViewPageAdapter = new MainCircleViewPageAdapter(activity,mainShouYeCircleModel);
        size = mainShouYeCircleModel.getData().size();
        hoursFragmentWidget.vpHoursCircle.setAdapter(mainCircleViewPageAdapter);
        hoursFragmentWidget.llyHoursCircle.setViewPager(hoursFragmentWidget.vpHoursCircle);
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
                            hoursFragmentWidget.vpHoursCircle.setCurrentItem(mPagerPosition);/*
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




    public class HoursFragmentWidget{
        public HoursFragmentWidget(Activity activity){
            ButterKnife.bind(this,activity);
        }
        @Bind(R.id.gv_shouye_hoursshop_first)
        NoScroolGridView gvShouyeHoursShopFirst;
        @Bind(R.id.gv_shouye_hoursshop_second)
        NoScroolGridView gvShouyeHoursShopSecond;
        @Bind(R.id.vp_hours_circle)
        ViewPager vpHoursCircle;
        @Bind(R.id.lly_hours_circle)
        CircleIndicator llyHoursCircle;
        @Bind(R.id.circle_progress)
        CircleProgressView circleProgress;
    }
}
