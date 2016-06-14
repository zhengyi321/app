package com.gototongcheng.Presenter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gototongcheng.application.R;
import com.gototongcheng.cookies.MyIniClass;

import butterknife.OnClick;

/**
 * topbar
 * Created by zhyan on 16/6/14.
 */
public class CommonTopBarPresenter{

    //activity 为MainActivity
    private Activity activity;
    public TopBarCommonWidget topBarCommonWidget;
    public TopBarSelectWidget topBarSelectWidget;
    private String currentPlace = "";

    public CommonTopBarPresenter(){

    }
    public CommonTopBarPresenter(Activity activity){
        this.activity = activity;
        initView();
    }
    private  void initView(){
        topBarCommonWidget = new TopBarCommonWidget();
        topBarSelectWidget = new TopBarSelectWidget();

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

         MyIniClass myIniClass = new MyIniClass(activity,"");
        currentPlace = myIniClass.ReadString("currentPlace","");

    }
    public void initTopBar(String type){
        switch (type){
            case "shouye":
                topBarCommonWidget.llyTotal.setVisibility(View.VISIBLE);
                topBarSelectWidget.llyTotal.setVisibility(View.GONE);
                topBarCommonWidget.imLeft.setBackgroundResource(R.mipmap.left_bill);
                topBarCommonWidget.llyTotal.setBackgroundResource(R.color.color_topbar_bg_black);
                topBarCommonWidget.tvLeft.setText("账单");
                if(!currentPlace.isEmpty() && (currentPlace != null)) {
                    topBarCommonWidget.tvCenter.setText(currentPlace);
                }

                topBarCommonWidget.tvRight.setBackgroundResource(R.mipmap.search);
                topBarCommonWidget.tvRight.setText("");
                break;
            case "register":
                topBarCommonWidget.llyTotal.setVisibility(View.VISIBLE);
                topBarSelectWidget.llyTotal.setVisibility(View.GONE);
                topBarCommonWidget.llyTotal.setBackgroundResource(R.color.color_topbar_bg_black);
                topBarCommonWidget.imLeft.setBackgroundResource(R.mipmap.back_arrow_white);
                topBarCommonWidget.tvLeft.setText("返回");
                topBarCommonWidget.tvCenter.setText("");

                topBarCommonWidget.tvRight.setBackgroundResource(R.color.transparent);
                topBarCommonWidget.tvRight.setText("登入");
                break;
            case "foods":
                topBarCommonWidget.llyTotal.setVisibility(View.GONE);
                topBarSelectWidget.llyTotal.setVisibility(View.VISIBLE);
                topBarSelectWidget.llyTotal.setBackgroundResource(R.color.white);
                topBarSelectWidget.ibLeft.setBackgroundResource(R.mipmap.back_arrow_black);
                topBarSelectWidget.tvCenter.setText("输入您想要的商品快速查找");
                topBarSelectWidget.ibRight.setBackgroundResource(R.mipmap.search_black);
                break;
            case "24hours":
                topBarCommonWidget.llyTotal.setVisibility(View.GONE);
                topBarSelectWidget.llyTotal.setVisibility(View.VISIBLE);
                topBarSelectWidget.llyTotal.setBackgroundResource(R.color.white);
                topBarSelectWidget.ibLeft.setBackgroundResource(R.mipmap.back_arrow_black);
                topBarSelectWidget.tvCenter.setText("输入您想要的商品快速查找");
                topBarSelectWidget.ibRight.setBackgroundResource(R.mipmap.search_black);
                break;
            case "waterbeer":
                topBarCommonWidget.llyTotal.setVisibility(View.GONE);
                topBarSelectWidget.llyTotal.setVisibility(View.VISIBLE);
                topBarSelectWidget.llyTotal.setBackgroundResource(R.color.white);
                topBarSelectWidget.ibLeft.setBackgroundResource(R.mipmap.back_arrow_black);
                topBarSelectWidget.tvCenter.setText("酒水狂欢，全场5折起,仅限7天");
                topBarSelectWidget.ibRight.setBackgroundResource(R.mipmap.search_black);
                break;
            case "tongcheng":
                topBarCommonWidget.llyTotal.setVisibility(View.VISIBLE);
                topBarSelectWidget.llyTotal.setVisibility(View.GONE);
                topBarCommonWidget.imLeft.setBackgroundResource(R.mipmap.back_arrow_white);
                topBarCommonWidget.llyTotal.setBackgroundResource(R.color.color_topbar_back_bg_green);
                topBarCommonWidget.tvLeft.setText("返回");
                topBarCommonWidget.tvCenter.setText("同城快递");

                topBarCommonWidget.tvRight.setBackgroundResource(R.mipmap.goto_pepole_head);
                topBarCommonWidget.tvRight.setText("");
                break;

        }
    }




    public class TopBarCommonWidget{
        public LinearLayout llyTotal;
        public LinearLayout llyCenter;
        public RelativeLayout rlyRight;
        public RelativeLayout rlyLeft;
        public ImageButton imLeft;
        public TextView tvLeft;

        public TextView tvCenter;

        public TextView tvRight;
    }

    public class TopBarSelectWidget{
        public LinearLayout llyTotal;
        public LinearLayout llyRight;
        public RelativeLayout rlyLeft;
        public ImageButton ibLeft;

        public TextView tvCenter;

        public ImageButton ibRight;
    }
}
