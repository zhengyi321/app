package com.gototongcheng.Presenter;

import android.app.Activity;
import android.support.v4.view.ViewPager;

import com.gototongcheng.application.R;
import com.gototongcheng.view.fragment.MainShouYeFragment;
import com.gototongcheng.widget.LinearLayout.CircleIndicator;
import com.gototongcheng.widget.gridview.NoScroolGridView;
import com.gototongcheng.widget.progressview.CircleProgressView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhyan on 16/6/14.
 */
public class HoursFragmentPresenter extends BasePresenter{

    private HoursFragmentWidget hoursFragmentWidget;
    public HoursFragmentPresenter(){

    }
    public HoursFragmentPresenter(Activity activity){
        initViews(activity);
    }
    protected void initViews(Activity activity){
        this.activity = activity;
        mainActivityPresenter = new MainActivityPresenter(activity, R.id.fly_content);
        hoursFragmentWidget = new HoursFragmentWidget(activity);
    }


    public void back(){
        mainActivityPresenter.showFragment(new MainShouYeFragment(activity));
    }

    public class HoursFragmentWidget{
        public HoursFragmentWidget(Activity activity){
            ButterKnife.bind(this,activity);
        }
        @Bind(R.id.gv_shouye_hoursshop_first)
        NoScroolGridView gvShouyeHoursShopFirst;
        @Bind(R.id.gv_shouye_hoursshop_second)
        NoScroolGridView gvShouyeHoursShopSecond;
        @Bind(R.id.vp_hours_circle)
        ViewPager vpHoursCircle;
        @Bind(R.id.lly_hours_circle)
        CircleIndicator llyHoursCircle;
        @Bind(R.id.circle_progress)
        CircleProgressView circleProgress;
    }
}
