package com.gototongcheng.view.fragment;

import android.app.Activity;
import android.support.v4.view.ViewPager;

import com.gototongcheng.Presenter.CommonSelectorBarPresenter;
import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.MainShouYeFragmentPresenter;
import com.gototongcheng.application.R;
import com.gototongcheng.widget.imageview.CircleIndicator;
import com.gototongcheng.widget.progressview.CircleProgressView;

import butterknife.Bind;

/**
 * Created by zhyan on 16/6/10.
 */
public class MainShouYeFragment extends BaseFragment{

    private MainShouYeFragmentPresenter mainShouYeFragmentPresenter;

    private CommonTopBarPresenter commonTopBarPresenter;
    private CommonSelectorBarPresenter commonSelectorBarPresenter;
    private Activity activity;
    public MainShouYeFragment(){

    }
    public MainShouYeFragment(Activity activity){
        this.activity = activity;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_main_shouye_lly;
    }

    @Override
    public void initViews() {
        mainShouYeFragmentPresenter = new MainShouYeFragmentPresenter(activity);
  //      mainShouYeFragmentPresenter.initCircleViewPager();
        mainShouYeFragmentPresenter.initGetDataFromNet();

        commonTopBarPresenter = new CommonTopBarPresenter(activity);
        commonSelectorBarPresenter = new CommonSelectorBarPresenter(activity);
    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("shouye");
        commonSelectorBarPresenter.initStyle("shouye");
    }

    public void onDestroy(){
        super.onDestroy();
        mainShouYeFragmentPresenter.onDestroy();
    }
    public void onResume(){
        super.onResume();
      //  mainShouYeFragmentPresenter.onDestroy();
        initViews();
    }
}
