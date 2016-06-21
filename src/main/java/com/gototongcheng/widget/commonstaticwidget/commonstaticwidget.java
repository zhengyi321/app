package com.gototongcheng.widget.commonstaticwidget;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gototongcheng.application.R;
import com.gototongcheng.widget.gridview.NoScroolGridView;
import com.gototongcheng.widget.LinearLayout.CircleIndicator;
import com.gototongcheng.widget.progressview.CircleProgressView;
import com.gototongcheng.widget.tab.PluginScrollView;

/**
 * Created by admin on 16/6/16.
 */
public class CommonStaticWidget {
    private Activity activity;
    public  BottomBarWidget  bottomBarWidget ;
    public  SelectorWidget selectorWidget ;
    public  TopBarCommonWidget topBarCommonWidget ;
    public  TopBarSelectWidget topBarSelectWidget ;
    public  MainShouYeWidget mainShouYeWidget ;
    public  TongChengFragmentWidget tongChengFragmentWidget ;
    public  TongChengSendingWidget tongChengSendingWidget ;
    public  WaterBeerWidget waterBeerWidget ;
    public CommonStaticWidget(){

    }

    public CommonStaticWidget(Activity activity){
        initViews(activity);
    }
    private void initViews(Activity activity){
        this.activity = activity;
        if(bottomBarWidget == null) {
            bottomBarWidget = new BottomBarWidget();
        }
        if(selectorWidget == null) {
            selectorWidget = new SelectorWidget();
        }
        if(topBarCommonWidget == null) {
            topBarCommonWidget = new TopBarCommonWidget();
        }
        if(topBarSelectWidget == null) {
            topBarSelectWidget = new TopBarSelectWidget();
        }
        if(mainShouYeWidget == null) {
            mainShouYeWidget = new MainShouYeWidget();
        }
        if(tongChengFragmentWidget == null) {
            tongChengFragmentWidget = new TongChengFragmentWidget();
        }
        if(tongChengSendingWidget == null) {
            tongChengSendingWidget = new TongChengSendingWidget();
        }
        if(waterBeerWidget == null) {
            waterBeerWidget = new WaterBeerWidget();
        }
        //底部栏
        bottomBarWidget.llyTotalBottom = (LinearLayout)activity.findViewById(R.id.lly_total_bottom);
        bottomBarWidget.rgBottom = (RadioGroup)activity.findViewById(R.id.rg_bottom);
        bottomBarWidget.rbShouYeBottom = (RadioButton)activity.findViewById(R.id.rb_shouye_bottom);
        bottomBarWidget.rbDinnerBottom = (RadioButton)activity.findViewById(R.id.rb_dinner_bottom);
        bottomBarWidget.rbShoppingBottom = (RadioButton)activity.findViewById(R.id.rb_shopping_bottom);
        bottomBarWidget.rbPersonCenterBottom = (RadioButton)activity.findViewById(R.id.rb_person_center_bottom);
        //底部栏
        //搜索栏
        selectorWidget.llyTotal =  (LinearLayout)activity.findViewById(R.id.lly_total_select);
        selectorWidget.ivSelectScan = (ImageView)activity.findViewById(R.id.iv_select_scan);
        selectorWidget.tvSelectContent = (TextView)activity.findViewById(R.id.tv_select_content);
        selectorWidget.tvSelectSearch = (TextView)activity.findViewById(R.id.tv_select_search);
        selectorWidget.llySelectSearch = (LinearLayout)activity.findViewById(R.id.lly_select_search);

        //搜索栏

        //顶部栏
        topBarCommonWidget.llyTotal = (LinearLayout)activity.findViewById(R.id.lly_total);
        topBarCommonWidget.llyCenter = (LinearLayout)activity.findViewById(R.id.lly_center);
        topBarCommonWidget.rlyLeft = (RelativeLayout)activity.findViewById(R.id.rly_left);
        topBarCommonWidget.rlyRight = (RelativeLayout)activity.findViewById(R.id.rly_right);
        topBarCommonWidget.imLeft = (ImageButton)activity.findViewById(R.id.ib_left);
        topBarCommonWidget.tvLeft = (TextView)activity.findViewById(R.id.tv_left);
        topBarCommonWidget.tvCenter = (TextView)activity.findViewById(R.id.tv_center);
        topBarCommonWidget.tvRight = (TextView) activity.findViewById(R.id.tv_right);

        topBarSelectWidget.llyTotal = (LinearLayout)activity.findViewById(R.id.lly_total_select);
        topBarSelectWidget.llyRight = (LinearLayout)activity.findViewById(R.id.lly_right_select);
        topBarSelectWidget.rlyLeft = (RelativeLayout)activity.findViewById(R.id.rly_left_select);
        topBarSelectWidget.ibLeft = (ImageButton)activity.findViewById(R.id.ib_left_select);
        topBarSelectWidget.tvCenter = (TextView)activity.findViewById(R.id.tv_center_select);
        topBarSelectWidget.ibRight = (ImageButton)activity.findViewById(R.id.ib_right_select);
        //顶部栏
        //首页
        mainShouYeWidget.mViewPager = (ViewPager)activity.findViewById(R.id.vp_common_circle);
        mainShouYeWidget.mCircleIndicator = (CircleIndicator)activity.findViewById(R.id.lly_common_circle);

        mainShouYeWidget.mCircleProgressView = (CircleProgressView) activity.findViewById(R.id.circle_progress);
        mainShouYeWidget.gvShouYeFirst = (NoScroolGridView)activity.findViewById(R.id.gv_shouye_first);
        mainShouYeWidget.llyMainShouYe = (LinearLayout)activity.findViewById(R.id.lly_shouye_main);
        mainShouYeWidget.linearLayoutManager = new LinearLayoutManager(activity);
        //首页

        //首页 同城快递
        tongChengFragmentWidget.llyTongchengSending = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_sending);
        tongChengFragmentWidget.llyTongchengGoodsTracking = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_goods_tracking);

        tongChengFragmentWidget.llyTongchengFeeCheck = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_fee_check);
        tongChengFragmentWidget.llyTongchengTimeCheck = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_time_check);

        tongChengFragmentWidget.llyTongchengSiteCheck = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_site_check);
        tongChengFragmentWidget.llyTongchengRangCheck = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_rang_check);
        //首页 同城快递

        //首页 同城快递 我要寄件
        tongChengSendingWidget.llyTongchengSendingSender = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_sending_sender);
        tongChengSendingWidget.llyTongchengSendingReceiver = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_sending_receiver);

        //首页 同城快递 我要寄件
        //酒水直送
        waterBeerWidget.viewPager = (ViewPager) activity.findViewById(R.id.vp_waterbeer) ;
        waterBeerWidget.mPluginScrollView = (PluginScrollView) activity.findViewById(R.id.horizontalScrollView);
        //酒水直送
    }




    //底部栏
    public  class BottomBarWidget{
        public LinearLayout llyTotalBottom;
        public RadioGroup rgBottom;
        public RadioButton rbShouYeBottom;
        public RadioButton rbDinnerBottom;
        public RadioButton rbShoppingBottom;
        public RadioButton rbPersonCenterBottom;
    }
    //底部栏

    //搜索栏
    public static class SelectorWidget{
        public LinearLayout llyTotal;
        public LinearLayout llySelectSearch;
        public ImageView ivSelectScan;
        public TextView tvSelectContent;
        public TextView tvSelectSearch;
    }
    //搜索栏

    //顶部栏
    public  class TopBarCommonWidget{
        public LinearLayout llyTotal;
        public LinearLayout llyCenter;
        public RelativeLayout rlyRight;
        public RelativeLayout rlyLeft;
        public ImageButton imLeft;
        public TextView tvLeft;

        public TextView tvCenter;

        public TextView tvRight;
    }

    public  class TopBarSelectWidget{
        public LinearLayout llyTotal;
        public LinearLayout llyRight;
        public RelativeLayout rlyLeft;
        public ImageButton ibLeft;

        public TextView tvCenter;

        public ImageButton ibRight;
    }
    //顶部栏

    //首页
    public class MainShouYeWidget{
        public LinearLayout llyMainShouYe;
        public ViewPager mViewPager;
        public CircleIndicator mCircleIndicator;
        public CircleProgressView mCircleProgressView;
        public NoScroolGridView gvShouYeFirst;
        public LinearLayoutManager linearLayoutManager;
    }
    //首页

    //首页 同城快递
    public  class TongChengFragmentWidget{
        public LinearLayout llyTongchengSending;
        public LinearLayout llyTongchengGoodsTracking;

        public LinearLayout llyTongchengFeeCheck;
        public LinearLayout llyTongchengTimeCheck;

        public LinearLayout llyTongchengSiteCheck;
        public LinearLayout llyTongchengRangCheck;
    }
    //首页 同城快递

    //首页 同城快递 我要寄件
    public  class TongChengSendingWidget{
        public LinearLayout llyTongchengSendingSender;
        public LinearLayout llyTongchengSendingReceiver;

    }

    //首页 同城快递 我要寄件
    //酒水直送
    public  class WaterBeerWidget{
        public ViewPager viewPager;
        public PluginScrollView mPluginScrollView;
    }
    //酒水直送
}
