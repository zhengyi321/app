package com.gototongcheng.Presenter;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gototongcheng.application.R;
import com.gototongcheng.view.fragment.CommonBaiDuMapFragment;
import com.gototongcheng.view.fragment.TongChengFragment;
import com.gototongcheng.widget.popupwindow.CommonPopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 16/6/16.
 */
public class TongChengSiteCheckFragmentPresenter extends BasePresenter implements View.OnClickListener{

    private TongChengSiteCheckFragmentWidget tongChengSiteCheckFragmentWidget;
    private CommonPopupWindow commonPopupWindow;
    private MainActivityPresenter mainActivityPresenter;
    public TongChengSiteCheckFragmentPresenter(){

    }
    public TongChengSiteCheckFragmentPresenter(Activity activity){
        initViews(activity);
    }

    @Override
    protected void initViews(Activity activity) {
        this.activity = activity;
        mainActivityPresenter = new MainActivityPresenter(activity, R.id.fly_content);
        if(tongChengSiteCheckFragmentWidget == null) {
            tongChengSiteCheckFragmentWidget = new TongChengSiteCheckFragmentWidget();
        }
        commonPopupWindow = new CommonPopupWindow();
        mainActivityPresenter = new MainActivityPresenter(activity,R.id.fly_content);
        tongChengSiteCheckFragmentWidget.llyCommonSiteFindNearby = (LinearLayout)activity.findViewById(R.id.lly_common_site_find_nearby);
        tongChengSiteCheckFragmentWidget.llyCommonAreaSelect = (LinearLayout)activity.findViewById(R.id.lly_common_area_select);
        tongChengSiteCheckFragmentWidget.llyTongChengSiteCheckTotal = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_site_check_total);
        tongChengSiteCheckFragmentWidget.tvCommonAreaSelectPlace = (TextView)activity.findViewById(R.id.tv_common_area_select_place);
        tongChengSiteCheckFragmentWidget.llyCommonAreaSelect.setOnClickListener(this);
        tongChengSiteCheckFragmentWidget.llyCommonSiteFindNearby.setOnClickListener(this);

    }
    public void back(){
        mainActivityPresenter.showFragment(new TongChengFragment(activity));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lly_common_area_select:
                List<String> dataList = new ArrayList<String>();
                dataList.add("白象地区");
                dataList.add("柳市地区");

                commonPopupWindow.OneItemPopup(R.id.tv_common_area_select_place,activity,tongChengSiteCheckFragmentWidget.llyTongChengSiteCheckTotal,"请选择地区",dataList);
                break;
            case R.id.lly_common_site_find_nearby:
                mainActivityPresenter.showFragment(new CommonBaiDuMapFragment(activity));
                break;
        }
    }

    public class TongChengSiteCheckFragmentWidget{
        public LinearLayout llyCommonAreaSelect;
        public TextView tvCommonAreaSelectPlace;
        public LinearLayout llyCommonSiteFindNearby;
        public LinearLayout llyTongChengSiteCheckTotal;


    }
}
