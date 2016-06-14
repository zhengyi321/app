package com.gototongcheng.view.activity;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.MainActivityPresenter;
import com.gototongcheng.application.R;
import com.gototongcheng.utils.NetWorkUtil;
import com.gototongcheng.view.fragment.FoodsFragment;
import com.gototongcheng.view.fragment.HoursShopFragment;
import com.gototongcheng.view.fragment.MainShouYeFragment;
import com.gototongcheng.view.fragment.RegisterCenterFragment;
import com.gototongcheng.view.fragment.TongChengFragment;
import com.gototongcheng.view.fragment.WaterBeerFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.fly_content)
     FrameLayout fly_content;

    @Bind(R.id.rb_shouye_bottom)
     RadioButton rbShouYeBottom;

    @Bind(R.id.rb_dinner_bottom)
     RadioButton rbDinnerBottom;

    @Bind(R.id.rb_shopping_bottom)
     RadioButton rbShoppingBottom;

    @Bind(R.id.rb_person_center_bottom)
     RadioButton rbPersonCenterBottom;


    private MainActivityPresenter mainPresenter;

 //   private CommonTopBarPresenter commonTopBarPresenter;
    private List<Fragment> fragmentList;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main_lly;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        mainPresenter = new MainActivityPresenter(this, R.id.fly_content);
   //     commonTopBarPresenter = new CommonTopBarPresenter(this);
            fragmentList = new ArrayList<Fragment>();
        if (NetWorkUtil.isNetworkConnected()){
            Fragment mainShouYeFragment = new MainShouYeFragment(this);
            Fragment registerFragment = new RegisterCenterFragment(this);

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
  //      commonTopBarPresenter.initTopBar("shouye");
    }
    @OnClick(R.id.rb_shouye_bottom)
    public void shouYeOnClick(){
        if(fragmentList.size() > 0) {
            mainPresenter.showFragment(fragmentList.get(0));

        }
    }

    @OnClick(R.id.rb_person_center_bottom)
    public void personCenterOnClick(){
        if(fragmentList.size() > 0) {
    //        mainPresenter.showFragment(fragmentList.get(1));
        }
    }

    @OnClick(R.id.rb_dinner_bottom)
    public void dinnerOnClick(){
        if(fragmentList.size() > 0) {
    //        mainPresenter.showFragment(fragmentList.get(5));
        }
    }
    @OnClick(R.id.rb_person_center_bottom)
    public void personCenter(){
        if(fragmentList.size() > 0) {
            //        mainPresenter.showFragment(fragmentList.get(5));
            mainPresenter.showFragment(fragmentList.get(1));
        }
    }

}
