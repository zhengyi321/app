package com.gototongcheng.Presenter;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gototongcheng.adapter.MainCircleViewPageAdapter;
import com.gototongcheng.adapter.MainShouYeFirstGVAdapter;
import com.gototongcheng.application.R;
import com.gototongcheng.mapping.MainShouYeMapper;
import com.gototongcheng.mapping.rxjava.ApiCallback;
import com.gototongcheng.mapping.rxjava.SubscriberCallBack;
import com.gototongcheng.model.CommonCircleModel;
import com.gototongcheng.model.MainShouYeFirstGridViewModel;
import com.gototongcheng.widget.gridview.NoScroolGridView;
import com.gototongcheng.widget.LinearLayout.CircleIndicator;
import com.gototongcheng.widget.progressview.CircleProgressView;

import java.util.Timer;
import java.util.TimerTask;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by zhyan on 16/6/10.
 */
public class MainShouYeFragmentPresenter extends BasePresenter{

//    private Context mContext;

    public  MainShouYeWidget mainShouYeWidget ;

    private MainShouYeMapper mainShouYeMapper;
    private MainCircleViewPageAdapter mainCircleViewPageAdapter;
    private MainShouYeFirstGVAdapter mainShouYeFirstGvAdapter;
    private Timer mTimer;
    private int size;
    private int mPagerPosition = 0;
    private BannerTask mTimerTask;
    private boolean mIsUserTouched = false;
    public MainShouYeFragmentPresenter(){

    }

    public MainShouYeFragmentPresenter(Activity activity){
   //     this.mContext = context;
        initViews(activity);
    }
    protected void initViews(Activity activity) {
        this.activity = activity;
        if(mainShouYeWidget == null){
            mainShouYeWidget  = new MainShouYeWidget();
        }
        mainShouYeWidget.vpCommonCircle = (ViewPager)activity.findViewById(R.id.vp_common_circle);
        mainShouYeWidget.llyCommonCircle = (CircleIndicator)activity.findViewById(R.id.lly_common_circle);

        mainShouYeWidget.mCircleProgressView = (CircleProgressView) activity.findViewById(R.id.circle_progress);
        mainShouYeWidget.gvShouYeFirst = (NoScroolGridView)activity.findViewById(R.id.gv_shouye_first);
        mainShouYeWidget.llyMainShouYe = (LinearLayout)activity.findViewById(R.id.lly_shouye_main);
        mainShouYeWidget.linearLayoutManager = new LinearLayoutManager(activity);

        initGetDataFromNet();

    }
    //后台数据
    private void initGetDataFromNet(){
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
                }).subscribe(new SubscriberCallBack<MainShouYeFirstGridViewModel>(new ApiCallback<MainShouYeFirstGridViewModel>() {
                    @Override
                    public void onSuccess(MainShouYeFirstGridViewModel model) {
                        initGridView(model);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        Toast.makeText(activity,"服务器连接错误",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCompleted() {
                        hideProgress();
                    }
                }));

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
        mainShouYeWidget.vpCommonCircle.setAdapter(mainCircleViewPageAdapter);
        mainShouYeWidget.llyCommonCircle.setViewPager(mainShouYeWidget.vpCommonCircle);
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
                            mainShouYeWidget.vpCommonCircle.setCurrentItem(mPagerPosition);/*
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
    public  class MainShouYeWidget{
        public LinearLayout llyMainShouYe;
        public ViewPager vpCommonCircle;
        public CircleIndicator llyCommonCircle;
        public CircleProgressView mCircleProgressView;
        public NoScroolGridView gvShouYeFirst;
        public LinearLayoutManager linearLayoutManager;
    }
}
