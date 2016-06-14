package com.gototongcheng.Presenter;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gototongcheng.application.R;

/**
 * 搜索栏
 * Created by zhyan on 16/6/14.
 */
public class CommonSelectorBarPresenter {

    private Activity activity;
    private  SelectorWidget widget;
    public CommonSelectorBarPresenter(){

    }
    public CommonSelectorBarPresenter(Activity activity){
        this.activity = activity;
        widget = new SelectorWidget();
        initViews();
    }
    private void initViews(){
        widget.llyTotal =  (LinearLayout)activity.findViewById(R.id.lly_total_select);
        widget.ivSelectScan = (ImageView)activity.findViewById(R.id.iv_select_scan);
        widget.tvSelectContent = (TextView)activity.findViewById(R.id.tv_select_content);
        widget.tvSelectSearch = (TextView)activity.findViewById(R.id.tv_select_search);
        widget.llySelectSearch = (LinearLayout)activity.findViewById(R.id.lly_select_search);
    }
    public void initStyle(String type){
        switch (type){
            case "shouye":
                widget.llySelectSearch.setBackgroundResource(R.drawable.shape_search_right_green_bg_40dp);
                widget.tvSelectSearch.setTextColor(activity.getResources().getColor(R.color.black));
                break;
            case "tongcheng":
                widget.llySelectSearch.setBackgroundResource(R.drawable.shape_search_right_black_bg_40dp);
                widget.tvSelectSearch.setTextColor(activity.getResources().getColor(R.color.white));

                break;
        }
    }
    private class SelectorWidget{
        public LinearLayout llyTotal;
        public LinearLayout llySelectSearch;
        public ImageView ivSelectScan;
        public TextView tvSelectContent;
        public TextView tvSelectSearch;
    }
}
