package com.gototongcheng.Presenter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gototongcheng.application.R;
import com.gototongcheng.cookies.MyIniClass;
import com.gototongcheng.view.activity.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * topbar
 * Created by zhyan on 16/6/14.
 */
public class CommonTopBarPresenter extends BasePresenter{

    //activity 为MainActivity
    public  TopBarCommonWidget topBarCommonWidget ;
    public  TopBarSelectWidget topBarSelectWidget ;
    private String currentPlace = "";
    public CommonTopBarPresenter(){

    }
    public CommonTopBarPresenter(Activity mActivity){
        if(mActivity != null) {
            initViews(mActivity);
        }
    }

    protected void initViews(Activity mActivity) {
        activity = mActivity;
        if(topBarCommonWidget == null){
            topBarCommonWidget  = new TopBarCommonWidget(activity);
        }
        if(topBarSelectWidget == null){
            topBarSelectWidget  = new TopBarSelectWidget(activity);
        }


        MyIniClass myIniClass = new MyIniClass(mActivity,"");
        currentPlace = myIniClass.ReadString("currentPlace","");
    }

    public void initTopBar(String type){
        switch (type){
            case "shouye":
                topBarCommonWidget.llyTotal.setVisibility(View.VISIBLE);
                topBarSelectWidget.llyTotal.setVisibility(View.GONE);
                topBarCommonWidget.ibLeft.setBackgroundResource(R.mipmap.left_bill);
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
                topBarCommonWidget.ibLeft.setBackgroundResource(R.mipmap.back_arrow_white);
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
                topBarCommonWidget.ibLeft.setBackgroundResource(R.mipmap.back_arrow_white);
                topBarCommonWidget.llyTotal.setBackgroundResource(R.color.color_topbar_back_bg_green);
                topBarCommonWidget.tvLeft.setText("返回");
                topBarCommonWidget.tvCenter.setText("同城快递");

                topBarCommonWidget.tvRight.setBackgroundResource(R.mipmap.goto_pepole_head);
                topBarCommonWidget.tvRight.setText("");
                break;
            case "login":
                topBarCommonWidget.llyTotal.setVisibility(View.VISIBLE);
                topBarSelectWidget.llyTotal.setVisibility(View.GONE);
                topBarCommonWidget.llyTotal.setBackgroundResource(R.color.color_topbar_bg_black);
                topBarCommonWidget.ibLeft.setBackgroundResource(R.mipmap.back_arrow_white);
                topBarCommonWidget.tvLeft.setText("返回");
                topBarCommonWidget.tvCenter.setText("");

                topBarCommonWidget.tvRight.setBackgroundResource(R.color.transparent);
                topBarCommonWidget.tvRight.setText("注册");
                break;
            case "tongchengrangcheck":
                topBarCommonWidget.llyTotal.setVisibility(View.VISIBLE);
                topBarSelectWidget.llyTotal.setVisibility(View.GONE);
                topBarCommonWidget.ibLeft.setBackgroundResource(R.mipmap.back_arrow_white);
                topBarCommonWidget.llyTotal.setBackgroundResource(R.color.color_topbar_back_bg_green);
                topBarCommonWidget.tvLeft.setText("返回");
                topBarCommonWidget.tvCenter.setText("范围查询");
                topBarCommonWidget.tvRight.setBackgroundResource(R.mipmap.goto_pepole_head);
                topBarCommonWidget.tvRight.setText("");
                break;
            case "tongchengsitecheck":
                topBarCommonWidget.llyTotal.setVisibility(View.VISIBLE);
                topBarSelectWidget.llyTotal.setVisibility(View.GONE);
                topBarCommonWidget.ibLeft.setBackgroundResource(R.mipmap.back_arrow_white);
                topBarCommonWidget.llyTotal.setBackgroundResource(R.color.color_topbar_back_bg_green);
                topBarCommonWidget.tvLeft.setText("返回");
                topBarCommonWidget.tvCenter.setText("网点查询");
                topBarCommonWidget.tvRight.setBackgroundResource(R.mipmap.goto_pepole_head);
                topBarCommonWidget.tvRight.setText("");
                break;
            case "tongchengtimecheck":
                topBarCommonWidget.llyTotal.setVisibility(View.VISIBLE);
                topBarSelectWidget.llyTotal.setVisibility(View.GONE);
                topBarCommonWidget.ibLeft.setBackgroundResource(R.mipmap.back_arrow_white);
                topBarCommonWidget.llyTotal.setBackgroundResource(R.color.color_topbar_back_bg_green);
                topBarCommonWidget.tvLeft.setText("返回");
                topBarCommonWidget.tvCenter.setText("时效查询");
                topBarCommonWidget.tvRight.setBackgroundResource(R.mipmap.goto_pepole_head);
                topBarCommonWidget.tvRight.setText("");
                break;
            case "tongchengfeecheck":
                topBarCommonWidget.llyTotal.setVisibility(View.VISIBLE);
                topBarSelectWidget.llyTotal.setVisibility(View.GONE);
                topBarCommonWidget.ibLeft.setBackgroundResource(R.mipmap.back_arrow_white);
                topBarCommonWidget.llyTotal.setBackgroundResource(R.color.color_topbar_back_bg_green);
                topBarCommonWidget.tvLeft.setText("返回");
                topBarCommonWidget.tvCenter.setText("运费查询");
                topBarCommonWidget.tvRight.setBackgroundResource(R.mipmap.goto_pepole_head);
                topBarCommonWidget.tvRight.setText("");
                break;
            case "tongchenggoodstracking":
                topBarCommonWidget.llyTotal.setVisibility(View.VISIBLE);
                topBarSelectWidget.llyTotal.setVisibility(View.GONE);
                topBarCommonWidget.ibLeft.setBackgroundResource(R.mipmap.back_arrow_white);
                topBarCommonWidget.llyTotal.setBackgroundResource(R.color.color_topbar_back_bg_green);
                topBarCommonWidget.tvLeft.setText("返回");
                topBarCommonWidget.tvCenter.setText("物流追踪");
                topBarCommonWidget.tvRight.setBackgroundResource(R.mipmap.goto_pepole_head);
                topBarCommonWidget.tvRight.setText("");
                break;
            case "tongchengsending":
                topBarCommonWidget.llyTotal.setVisibility(View.VISIBLE);
                topBarSelectWidget.llyTotal.setVisibility(View.GONE);
                topBarCommonWidget.ibLeft.setBackgroundResource(R.mipmap.back_arrow_white);
                topBarCommonWidget.llyTotal.setBackgroundResource(R.color.color_topbar_back_bg_green);
                topBarCommonWidget.tvLeft.setText("返回");
                topBarCommonWidget.tvCenter.setText("同城快递");
                topBarCommonWidget.tvRight.setBackgroundResource(R.mipmap.goto_pepole_head);
                topBarCommonWidget.tvRight.setText("");
                break;

            case "tongchengsendingbuildreceiveaddress":
                topBarCommonWidget.llyTotal.setVisibility(View.VISIBLE);
                topBarSelectWidget.llyTotal.setVisibility(View.GONE);
                topBarCommonWidget.ibLeft.setBackgroundResource(R.mipmap.back_arrow_white);
                topBarCommonWidget.llyTotal.setBackgroundResource(R.color.color_topbar_back_bg_green);
                topBarCommonWidget.tvLeft.setText("返回");
                topBarCommonWidget.tvCenter.setText("新建收件地址");
                topBarCommonWidget.tvRight.setBackgroundResource(R.color.transparent);
                topBarCommonWidget.tvRight.setText("保存");
                break;
            case "tongchengsendingbuildsendaddress":
                topBarCommonWidget.llyTotal.setVisibility(View.VISIBLE);
                topBarSelectWidget.llyTotal.setVisibility(View.GONE);
                topBarCommonWidget.ibLeft.setBackgroundResource(R.mipmap.back_arrow_white);
                topBarCommonWidget.llyTotal.setBackgroundResource(R.color.color_topbar_back_bg_green);
                topBarCommonWidget.tvLeft.setText("返回");
                topBarCommonWidget.tvCenter.setText("新建寄件地址");
                topBarCommonWidget.tvRight.setBackgroundResource(R.color.transparent);
                topBarCommonWidget.tvRight.setText("保存");
                break;

        }
    }






    public  class TopBarCommonWidget{

        public TopBarCommonWidget(Activity activity){
            ButterKnife.bind(this,activity);
        }
        @Bind(R.id.lly_total)
        LinearLayout llyTotal;
        @Bind(R.id.lly_center)
        LinearLayout llyCenter;
        @Bind(R.id.rly_right)
        RelativeLayout rlyRight;
        @Bind(R.id.rly_left)
        RelativeLayout rlyLeft;
        @Bind(R.id.ib_left)
        ImageButton ibLeft;
        @Bind(R.id.tv_left)
        TextView tvLeft;
        @Bind(R.id.tv_center)
        TextView tvCenter;
        @Bind(R.id.tv_right)
        TextView tvRight;
    }

    public  class TopBarSelectWidget{

        public TopBarSelectWidget(Activity activity){
            ButterKnife.bind(this,activity);
        }
        @Bind(R.id.lly_total_select)
        LinearLayout llyTotal;
        @Bind(R.id.lly_right_select)
        LinearLayout llyRight;
        @Bind(R.id.rly_left_select)
        RelativeLayout rlyLeft;
        @Bind(R.id.ib_left_select)
        ImageButton ibLeft;
        @Bind(R.id.tv_center_select)
        TextView tvCenter;
        @Bind(R.id.ib_right_select)
        ImageButton ibRight;
    }
}
