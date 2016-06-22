package com.gototongcheng.Presenter;

import android.app.Activity;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gototongcheng.application.R;
import com.gototongcheng.view.fragment.FoodsFragment;
import com.gototongcheng.view.fragment.MainShouYeFragment;
import com.gototongcheng.view.views.ViewsWaterBeerLLY;
import com.gototongcheng.widget.tab.PluginScrollView;
import com.gototongcheng.widget.tab.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnPageChange;

/**
 * Created by admin on 16/6/14.
 */
public class WaterBeerFragmentPresenter extends BasePresenter{

    public  WaterBeerWidget widget ;
    private ViewPagerAdapter viewPagerAdapter;
    private List<View> viewList;
    private List<String> dataList;
    public WaterBeerFragmentPresenter(Activity activity){

        initViews(activity);
    }



    protected void initViews(Activity activity) {
        this.activity = activity;
        initTabAndViews();
        mainActivityPresenter = new MainActivityPresenter(activity,R.id.fly_content);
    }

    public void back(){
        mainActivityPresenter.showFragment(new MainShouYeFragment(activity));
    }


    private void initTabAndViews(){
        if(widget == null) {
            widget = new WaterBeerWidget(activity);
        }

        initDataList();
        initViewList();
        //   mTabLayout = (SlidingTabLayout) getView().findViewById(R.id.ctl_waterbeer) ;
        viewPagerAdapter = new ViewPagerAdapter();
        viewPagerAdapter.setList(viewList);
        widget.viewPager.setAdapter(viewPagerAdapter);
        widget.viewPager.setCurrentItem(0);
        // mPluginScrollView = new PluginScrollView(this, viewPager, testList);

        widget.mPluginScrollView.setDataList(dataList);
        widget.mPluginScrollView.setViewPager(widget.viewPager);
        postInit();
    }
    private void initViewList() {

        ViewsWaterBeerLLY viewsWaterBeerLLY;
        viewList = new ArrayList<View>();
        for (int i = 0; i < dataList.size(); i++) {
     //       pluginScrollView = new PluginScrollView(activity);
            viewsWaterBeerLLY = new ViewsWaterBeerLLY(activity,activity.getLayoutInflater());
            viewList.add(viewsWaterBeerLLY);
        }

    }
    private void initDataList(){
        dataList = new ArrayList<String>();
        dataList.add("快速选酒");
        dataList.add("白酒");
        dataList.add("葡萄酒");
        dataList.add("洋酒");
        dataList.add("啤酒");
        dataList.add("黄酒");
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



    public  class WaterBeerWidget{
        public WaterBeerWidget(Activity activity){
            ButterKnife.bind(this,activity);
        }
        @Bind(R.id.vp_waterbeer)
         ViewPager viewPager;
        @Bind(R.id.horizontalScrollView)
         PluginScrollView mPluginScrollView;
    }
}
