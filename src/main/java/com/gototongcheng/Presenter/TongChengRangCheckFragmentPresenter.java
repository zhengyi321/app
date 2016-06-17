package com.gototongcheng.Presenter;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gototongcheng.application.R;
import com.gototongcheng.view.fragment.TongChengFragment;
import com.gototongcheng.widget.popupwindow.CommonPopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 16/6/16.
 */
public class TongChengRangCheckFragmentPresenter extends BasePresenter implements View.OnClickListener{

    public TongChengRangCheckFragmentWidget tongChengRangCheckFragmentWidget;
    private CommonPopupWindow commonPopupWindow;
    public TongChengRangCheckFragmentPresenter(){

    }
    public TongChengRangCheckFragmentPresenter(Activity activity){
        initViews(activity);
    }

    @Override
    protected void initViews(Activity activity) {
        this.activity = activity;
        mainActivityPresenter = new MainActivityPresenter(activity, R.id.fly_content);
        if(tongChengRangCheckFragmentWidget == null) {
            tongChengRangCheckFragmentWidget = new TongChengRangCheckFragmentWidget();
        }
        commonPopupWindow = new CommonPopupWindow();
        tongChengRangCheckFragmentWidget.llyCommonAreaSelect = (LinearLayout)activity.findViewById(R.id.lly_common_area_select);
        tongChengRangCheckFragmentWidget.tvCommonAreaSelectPlace = (TextView)activity.findViewById(R.id.tv_common_area_select_place);
        tongChengRangCheckFragmentWidget.llyTongChengRangCheckTotal = (LinearLayout)activity.findViewById(R.id.lly_tongcheng_rang_check_total);
        tongChengRangCheckFragmentWidget.llyCommonAreaSelect.setOnClickListener(this);
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
                commonPopupWindow.OneItemPopup(R.id.tv_common_area_select_place,activity,tongChengRangCheckFragmentWidget.llyTongChengRangCheckTotal,"地区选择",dataList);
                break;
        }
    }

    public class TongChengRangCheckFragmentWidget{
        public LinearLayout llyCommonAreaSelect;
        public TextView tvCommonAreaSelectPlace;
        public LinearLayout llyTongChengRangCheckTotal;
    }
}
