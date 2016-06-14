package com.gototongcheng.Presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gototongcheng.application.R;
import com.gototongcheng.view.fragment.MainShouYeFragment;
import com.gototongcheng.widget.tab.PluginScrollView;
import com.gototongcheng.widget.tab.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 16/6/14.
 */
public class WaterBeerFragmentPresenter {

    private WaterBeerWidget widget;
    private MainActivityPresenter mainActivityPresenter;
    ViewPagerAdapter viewPagerAdapter;
    List<View> testList;
    private Activity activity;
    public WaterBeerFragmentPresenter(Activity activity){
        this.activity = activity;
        initViews();
    }
    public void initViews(){
        widget = new WaterBeerWidget();
        initTabAndViews();
        mainActivityPresenter = new MainActivityPresenter(activity,R.id.fly_content);
    }

    public void back(){

        mainActivityPresenter.showFragment(new MainShouYeFragment(activity));
    }


    private void initTabAndViews(){
        widget.viewPager = (ViewPager) activity.findViewById(R.id.vp_waterbeer) ;
        preInit();
        //   mTabLayout = (SlidingTabLayout) getView().findViewById(R.id.ctl_waterbeer) ;
        viewPagerAdapter = new ViewPagerAdapter();
        viewPagerAdapter.setList(testList);
        widget.viewPager.setAdapter(viewPagerAdapter);
        widget.viewPager.setCurrentItem(0);
        // mPluginScrollView = new PluginScrollView(this, viewPager, testList);
        widget.mPluginScrollView = (PluginScrollView) activity.findViewById(R.id.horizontalScrollView);
        widget.mPluginScrollView.setTestList(testList);
        widget.mPluginScrollView.setViewPager(widget.viewPager);
        postInit();
    }
    private void preInit() {

        TextView textView;
        testList = new ArrayList<View>();
        for (int i = 0; i < 10; i++) {
            textView = new TextView(activity);
            textView.setText("ViewPager ==>" + i);
            testList.add(textView);
        }

    }

    private void postInit() {
        widget.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                Log.d("k", "onPageSelected - " + arg0);
                widget.mPluginScrollView.buttonSelected(arg0);
                widget.viewPager.setCurrentItem(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
                Log.d("k", "onPageScrolled - " + arg0);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
                Log.d("k", "onPageScrollStateChanged - " + arg0);
                // 状态有三个0空闲，1是增在滑行中，2目标加载完毕
            }
        });

    }


    private class WaterBeerWidget{
        public ViewPager viewPager;
        public PluginScrollView mPluginScrollView;
    }
}
