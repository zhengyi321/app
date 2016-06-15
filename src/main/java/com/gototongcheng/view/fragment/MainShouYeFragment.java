package com.gototongcheng.view.fragment;

import android.app.Activity;

import com.gototongcheng.Presenter.CommonBottomBarPresenter;
import com.gototongcheng.Presenter.CommonSelectorBarPresenter;
import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.MainShouYeFragmentPresenter;
import com.gototongcheng.application.R;

/**
 * Created by zhyan on 16/6/10.
 */
public class MainShouYeFragment extends BaseFragment{

    private MainShouYeFragmentPresenter mainShouYeFragmentPresenter;

    private CommonTopBarPresenter commonTopBarPresenter;
    private CommonSelectorBarPresenter commonSelectorBarPresenter;
    private CommonBottomBarPresenter commonBottomBarPresenter;
    private Activity activity;
    public MainShouYeFragment(){

    }
    public MainShouYeFragment(Activity activity){
        this.activity = activity;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_main_shouye_sv;
    }

    @Override
    public void initViews() {
        mainShouYeFragmentPresenter = new MainShouYeFragmentPresenter(activity);
  //      mainShouYeFragmentPresenter.initCircleViewPager();
        mainShouYeFragmentPresenter.initGetDataFromNet();

        commonTopBarPresenter = new CommonTopBarPresenter(activity);
        commonSelectorBarPresenter = new CommonSelectorBarPresenter(activity);
        commonBottomBarPresenter = new CommonBottomBarPresenter(activity);
    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("shouye");
        commonSelectorBarPresenter.initStyle("shouye");
        commonBottomBarPresenter.initBottomShouYeReset();
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
