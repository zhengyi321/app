package com.gototongcheng.view.fragment;

import android.support.v4.view.ViewPager;

import com.gototongcheng.Presenter.MainShouYeFragmentPresenter;
import com.gototongcheng.application.R;
import com.gototongcheng.widget.imageview.CircleIndicator;
import com.gototongcheng.widget.progressview.CircleProgressView;

import butterknife.Bind;

/**
 * Created by zhyan on 16/6/10.
 */
public class MainShouYeFragment extends BaseFragment{

    @Bind(R.id.vp_shouye_circle)
    ViewPager vpShouYeCircle;

    @Bind(R.id.ci_shouye)
    CircleIndicator ciShouYe;

    @Bind(R.id.circle_progress)
    CircleProgressView circleProgressView;
    private MainShouYeFragmentPresenter mainShouYeFragmentPresenter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_main_shouye_lly;
    }

    @Override
    public void initViews() {
        mainShouYeFragmentPresenter = new MainShouYeFragmentPresenter(getActivity());
  //      mainShouYeFragmentPresenter.initCircleViewPager();
        mainShouYeFragmentPresenter.initGetDataFromNet();
    }

    public void onDestroy(){
        super.onDestroy();
        mainShouYeFragmentPresenter.onDestroy();
    }
}
