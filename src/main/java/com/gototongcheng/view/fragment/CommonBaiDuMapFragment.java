package com.gototongcheng.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;

import com.gototongcheng.Presenter.CommonBaiduMapPresenter;
import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.application.R;

/**
 * Created by admin on 16/6/17.
 */
public class CommonBaiDuMapFragment extends BaseFragment{

    private CommonBaiduMapPresenter commonBaiduMapPresenter;
    private CommonTopBarPresenter commonTopBarPresenter;
    public CommonBaiDuMapFragment(){

    }
    @SuppressLint("ValidFragment")//解决fragment 打开地图的参数错误问题
    public CommonBaiDuMapFragment(Activity activity){
        this.activity = activity;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_common_baidu_map_lly;
    }

    @Override
    public void initViews() {
        commonBaiduMapPresenter = new CommonBaiduMapPresenter(activity);
        commonTopBarPresenter = new CommonTopBarPresenter(activity);
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
   //     commonBaiduMapPresenter.commonBaiDuMapWidget.mvCommonBaiDu.onDestroy();
    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        commonBaiduMapPresenter.commonBaiDuMapWidget.mvCommonBaiDu.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        commonBaiduMapPresenter.commonBaiDuMapWidget.mvCommonBaiDu.onPause();
    }
}
