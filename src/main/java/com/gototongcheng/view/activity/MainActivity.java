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
import com.gototongcheng.view.fragment.CommonBaiDuMapFragment;
import com.gototongcheng.view.fragment.FoodsFragment;
import com.gototongcheng.view.fragment.HoursShopFragment;
import com.gototongcheng.view.fragment.LoginCenterFragment;
import com.gototongcheng.view.fragment.MainShouYeFragment;
import com.gototongcheng.view.fragment.RegisterCenterFragment;
import com.gototongcheng.view.fragment.TongChengFeeCheckFragment;
import com.gototongcheng.view.fragment.TongChengFragment;
import com.gototongcheng.view.fragment.TongChengGoodsTrackingFragment;
import com.gototongcheng.view.fragment.TongChengRangCheckFragment;
import com.gototongcheng.view.fragment.TongChengSendingFragment;
import com.gototongcheng.view.fragment.TongChengSiteCheckFragment;
import com.gototongcheng.view.fragment.TongChengTimeCheckFragment;
import com.gototongcheng.view.fragment.WaterBeerFragment;
import com.gototongcheng.widget.commonstaticwidget.CommonStaticWidget;

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
    private CommonTopBarPresenter commonTopBarPresenter;



    @Override
    public int getLayoutId() {
        return R.layout.activity_main_lly;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        commonTopBarPresenter = new CommonTopBarPresenter(this);
        mainPresenter = new MainActivityPresenter(this, R.id.fly_content);
        mainPresenter.showFragment(new MainShouYeFragment(this));

    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("shouye");
    }
    @OnClick(R.id.rb_shouye_bottom)
    public void shouYeOnClick(){

        mainPresenter.showFragment(new MainShouYeFragment(this));

    }

    @OnClick(R.id.rb_shopping_bottom)
    public void shoppingOnClick(){

    }

    @OnClick(R.id.rb_dinner_bottom)
    public void dinnerOnClick(){
        mainPresenter.showFragment(new CommonBaiDuMapFragment(this));
    }
    @OnClick(R.id.rb_person_center_bottom)
    public void personCenter(){

        mainPresenter.showFragment(new LoginCenterFragment(this));

    }

}
