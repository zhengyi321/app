package com.gototongcheng.view.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.WaterBeerFragmentPresenter;
import com.gototongcheng.application.R;
import com.gototongcheng.widget.tab.PluginScrollView;
import com.gototongcheng.widget.tab.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhyan on 16/6/13.
 */
public class WaterBeerFragment extends BaseFragment implements View.OnClickListener{

    private WaterBeerFragmentPresenter waterBeerFragmentPresenter;
    private Activity activity ;
    private CommonTopBarPresenter commonTopBarPresenter;
    public WaterBeerFragment(){

    }
    public WaterBeerFragment(Activity activity){
        this.activity = activity;
        commonTopBarPresenter = new CommonTopBarPresenter(activity);
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_main_shouye_waterbeer_lly;
    }

    @Override
    public void initViews() {
        waterBeerFragmentPresenter = new WaterBeerFragmentPresenter(getActivity());
        commonTopBarPresenter.topBarSelectWidget.rlyLeft.setOnClickListener(this);

    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("waterbeer");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rly_left_select:
                waterBeerFragmentPresenter.back();
                break;
        }
    }
}
