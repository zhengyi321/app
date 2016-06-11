package com.gototongcheng.view.activity;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.gototongcheng.Presenter.MainActivityPresenter;
import com.gototongcheng.application.R;
import com.gototongcheng.utils.NetWorkUtil;
import com.gototongcheng.view.fragment.MainShouYeFragment;
import com.gototongcheng.view.fragment.RegisterCenterFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.fly_content)
     FrameLayout fly_content;

    @Bind(R.id.rb_shouye)
     RadioButton rbShouYe;

    @Bind(R.id.rb_dinner)
     RadioButton rbDinner;

    @Bind(R.id.rb_shopping)
     RadioButton rbShopping;

    @Bind(R.id.rb_person_center)
     RadioButton rbPersonCenter;


    private MainActivityPresenter mainPresenter;

    private List<Fragment> fragmentList;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main_lly;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

            mainPresenter = new MainActivityPresenter(this, R.id.fly_content);
            fragmentList = new ArrayList<Fragment>();
        if (NetWorkUtil.isNetworkConnected()){
            Fragment mainShouYeFragment = new MainShouYeFragment();
            Fragment registerFragment = new RegisterCenterFragment();
            fragmentList.add(mainShouYeFragment);
            fragmentList.add(registerFragment);

            mainPresenter.showFragment(fragmentList.get(0));
        }else{
            NetWorkUtil.ShowDialog(this, "设置网络",
                    "请检查你的网络设置");
     //       this.finish();
        }
    }

    @Override
    public void initToolBar() {

    }
    @OnClick(R.id.rb_shouye)
    public void shouYeOnClick(){
        if(fragmentList.size() > 0) {
            mainPresenter.showFragment(fragmentList.get(0));
        }
    }

    @OnClick(R.id.rb_person_center)
    public void personCenter(){
        if(fragmentList.size() > 1) {
            mainPresenter.showFragment(fragmentList.get(1));
        }
    }
}
