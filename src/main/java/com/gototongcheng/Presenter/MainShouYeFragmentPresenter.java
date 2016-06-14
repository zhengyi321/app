package com.gototongcheng.Presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gototongcheng.adapter.MainCircleViewPageAdapter;
import com.gototongcheng.adapter.MainShouYeFirstGVAdapter;
import com.gototongcheng.application.GotoCityApp;
import com.gototongcheng.application.R;
import com.gototongcheng.mapping.MainShouYeMapper;
import com.gototongcheng.mapping.TestMapper;
import com.gototongcheng.model.MainShouYeCircleModel;
import com.gototongcheng.model.MainShouYeFirstGridViewModel;
import com.gototongcheng.model.Test;
import com.gototongcheng.utils.LogUtil;
import com.gototongcheng.widget.GridView.NoScroolGridView;
import com.gototongcheng.widget.imageview.CircleIndicator;
import com.gototongcheng.widget.progressview.CircleProgressView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhyan on 16/6/10.
 */
public class MainShouYeFragmentPresenter {

//    private Context mContext;

    private MainShouYeWidget mainShouYeWidget;

    private MainShouYeMapper mainShouYeMapper;
    private MainCircleViewPageAdapter mainCircleViewPageAdapter;
    private MainShouYeFirstGVAdapter mainShouYeFirstGvAdapter;
    private Timer mTimer;
    private int size;
    private int mPagerPosition = 0;
    private BannerTask mTimerTask;
    private Activity activity;
    private boolean mIsUserTouched = false;

    public MainShouYeFragmentPresenter(Activity mActivity){
   //     this.mContext = context;
        mainShouYeWidget = new MainShouYeWidget();
        mainShouYeWidget.mViewPager = (ViewPager)mActivity.findViewById(R.id.vp_shouye_circle);
        mainShouYeWidget.mCircleIndicator = (CircleIndicator)mActivity.findViewById(R.id.ci_shouye);
        this.activity = mActivity;
        mainShouYeWidget.mCircleProgressView = (CircleProgressView) mActivity.findViewById(R.id.circle_progress);
        mainShouYeWidget.gvShouYeFirst = (NoScroolGridView)mActivity.findViewById(R.id.gv_shouye_first);
        mainShouYeWidget.llyMainShouYe = (LinearLayout)mActivity.findViewById(R.id.lly_shouye_main);
        mainShouYeWidget.linearLayoutManager = new LinearLayoutManager(mActivity);
    }

    //后台数据
    public void initGetDataFromNet(){
        mainShouYeMapper  = new MainShouYeMapper();

        getCircleViewFromNet();
        getFirstGridviewIcoFromNet();
      //返回顶部
    }


    //获取gridview图标
    private void getFirstGridviewIcoFromNet(){
        mainShouYeMapper.getMainShouYeFirstGridView()
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
                .subscribe(new Action1<MainShouYeFirstGridViewModel>() {
                    @Override
                    public void call(MainShouYeFirstGridViewModel model) {
                        hideProgress();
                        initGridView(model);
                    }
                })
        ;

    }


    //初始化gridview
    private void initGridView(MainShouYeFirstGridViewModel model){

        mainShouYeFirstGvAdapter = new MainShouYeFirstGVAdapter(activity,model);
        mainShouYeWidget.gvShouYeFirst.setAdapter(mainShouYeFirstGvAdapter);

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
                .subscribe(new Action1<MainShouYeCircleModel>() {
                    @Override
                    public void call(MainShouYeCircleModel mainShouYeCircleModel) {
                        hideProgress();
                        initCircleViewPager(mainShouYeCircleModel);
                    }
                })
        ;
    }

    //轮播页面初始化
    public void initCircleViewPager(MainShouYeCircleModel mainShouYeCircleModel){
        mainCircleViewPageAdapter = new MainCircleViewPageAdapter(activity,mainShouYeCircleModel);
        size = mainShouYeCircleModel.getData().size();
        mainShouYeWidget.mViewPager.setAdapter(mainCircleViewPageAdapter);
        mainShouYeWidget.mCircleIndicator.setViewPager(mainShouYeWidget.mViewPager);
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
                            mainShouYeWidget.mViewPager.setCurrentItem(mPagerPosition);/*
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

        mainShouYeWidget.mCircleProgressView.setVisibility(View.VISIBLE);
        mainShouYeWidget.mCircleProgressView.spin();
        //      mRecyclerView.setVisibility(View.GONE);
    }

    public void hideProgress()
    {

        mainShouYeWidget.mCircleProgressView.setVisibility(View.GONE);
        mainShouYeWidget.mCircleProgressView.stopSpinning();
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
    private class MainShouYeWidget{
        public LinearLayout llyMainShouYe;
        public ViewPager mViewPager;
        public CircleIndicator mCircleIndicator;
        public CircleProgressView mCircleProgressView;
        public NoScroolGridView gvShouYeFirst;
        public LinearLayoutManager linearLayoutManager;
    }
}
