package com.gototongcheng.Presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
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
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by zhyan on 16/6/10.
 */
public class MainShouYeFragmentPresenter {

//    private Context mContext;
    private ViewPager mViewPager;
    private CircleIndicator mCircleIndicator;
    private CircleProgressView mCircleProgressView;
    private MainShouYeMapper mainShouYeMapper;
    private NoScroolGridView gvShouYeFirst;

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
        this.mViewPager = (ViewPager)mActivity.findViewById(R.id.vp_shouye_circle);
        this.mCircleIndicator = (CircleIndicator)mActivity.findViewById(R.id.ci_shouye);
        this.activity = mActivity;
        this.mCircleProgressView = (CircleProgressView) mActivity.findViewById(R.id.circle_progress);
        this.gvShouYeFirst = (NoScroolGridView)mActivity.findViewById(R.id.gv_shouye_first);
    }

    //测试后台数据
    public void initGetDataFromNet(){
        mainShouYeMapper  = new MainShouYeMapper();
        getCircleViewFromNet();
        initGridView();

    }


    //初始化gridview
    private void initGridView(){
        List<MainShouYeFirstGridViewModel> modelList = new ArrayList<MainShouYeFirstGridViewModel>();
        MainShouYeFirstGridViewModel model = new MainShouYeFirstGridViewModel();
        model.setPic("http://avatar.csdn.net/0/2/9/1_ztp800201.jpg");
        model.setName("测试1");
        modelList.add(model);
        modelList.add(model);
        modelList.add(model);
        modelList.add(model);
        modelList.add(model);
        modelList.add(model);
        modelList.add(model);
        modelList.add(model);
        mainShouYeFirstGvAdapter = new MainShouYeFirstGVAdapter(activity,modelList);
        gvShouYeFirst.setAdapter(mainShouYeFirstGvAdapter);

    }



    //从后台获取轮播页面
    private void getCircleViewFromNet(){
        mainShouYeMapper.getCirclePic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(activity,"网络连接错误",Toast.LENGTH_LONG).show();
                    }
                })
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
        mViewPager.setAdapter(mainCircleViewPageAdapter);
        mCircleIndicator.setViewPager(mViewPager);
        startViewPagerRun();

    }
    public void startViewPagerRun()
    {
        //执行ViewPager进行轮播
        mTimer = new Timer();
        mTimerTask = new BannerTask();
     //   mTimer.schedule(mTimerTask, 5000, 0000);
        mTimer.schedule(mTimerTask, 1000,1000);
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
                            if (mPagerPosition == size - 1)
                            {
                                mViewPager.setCurrentItem(size - 1, false);
                            } else
                            {
                                mViewPager.setCurrentItem(mPagerPosition);
                            }
                        }
                    });
                }
            }

        }
    }

    private void showProgress()
    {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        //      mRecyclerView.setVisibility(View.GONE);
    }

    public void hideProgress()
    {

        mCircleProgressView.setVisibility(View.GONE);
        mCircleProgressView.stopSpinning();
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
}
