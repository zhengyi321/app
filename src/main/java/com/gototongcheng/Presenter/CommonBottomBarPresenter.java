package com.gototongcheng.Presenter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gototongcheng.application.R;
import com.gototongcheng.view.activity.MainActivity;

/**
 * 底部栏
 * Created by zhyan on 16/6/14.
 */
public class CommonBottomBarPresenter extends BasePresenter{

    public  BottomBarWidget  bottomBarWidget ;
    public CommonBottomBarPresenter(){

    }
    public CommonBottomBarPresenter(Activity activity){
        if(activity != null) {
            initViews(activity);
        }
    }
    protected void initViews(Activity activity){
        this.activity = activity;
        if(bottomBarWidget == null){
            bottomBarWidget  = new BottomBarWidget();
        }
        bottomBarWidget.llyTotalBottom = (LinearLayout)activity.findViewById(R.id.lly_total_bottom);
        bottomBarWidget.rgBottom = (RadioGroup)activity.findViewById(R.id.rg_bottom);
        bottomBarWidget.rbShouYeBottom = (RadioButton)activity.findViewById(R.id.rb_shouye_bottom);
        bottomBarWidget.rbDinnerBottom = (RadioButton)activity.findViewById(R.id.rb_dinner_bottom);
        bottomBarWidget.rbShoppingBottom = (RadioButton)activity.findViewById(R.id.rb_shopping_bottom);
        bottomBarWidget.rbPersonCenterBottom = (RadioButton)activity.findViewById(R.id.rb_person_center_bottom);
  //      initBottom();
    }



    public void initBottomShouYeReset(){
        bottomBarWidget.rbShouYeBottom.setChecked(true);
        bottomBarWidget.rbDinnerBottom.setChecked(false);
        bottomBarWidget.rbShoppingBottom.setChecked(false);
        bottomBarWidget.rbPersonCenterBottom.setChecked(false);
    }
    public void initBottom(){
        bottomBarWidget.rgBottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_shouye_bottom:
               //         bottomBarWidget.rbShouYeBottom.setCompoundDrawablesRelativeWithIntrinsicBounds(null,activity.getResources().getDrawable(R.mipmap.bottom_second_active),null,null);
               //         bottomBarWidget.rbShouYeBottom.setText("测试选中");
               //         bottomBarWidget.rbDinnerBottom.setCompoundDrawablesRelativeWithIntrinsicBounds(null,activity.getResources().getDrawable(R.mipmap.bottom_third_normal),null,null);
               //         bottomBarWidget.rbDinnerBottom.setText("测试未选中");
                        break;

                }
            }
        });
    }



    public   class BottomBarWidget{
        public LinearLayout llyTotalBottom;
        public RadioGroup rgBottom;
        public RadioButton rbShouYeBottom;
        public RadioButton rbDinnerBottom;
        public RadioButton rbShoppingBottom;
        public RadioButton rbPersonCenterBottom;
    }

}
