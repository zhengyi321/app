package com.gototongcheng.Presenter;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gototongcheng.application.R;
import com.gototongcheng.mapping.ShouYeTongChengMapper;
import com.gototongcheng.mapping.rxjava.ApiCallback;
import com.gototongcheng.mapping.rxjava.SubscriberCallBack;
import com.gototongcheng.model.BaseModel;
import com.gototongcheng.view.fragment.CommonBaiDuMapFragment;
import com.gototongcheng.view.fragment.TongChengFragment;
import com.gototongcheng.widget.popupwindow.CommonPopupWindow;
import com.gototongcheng.widget.progressview.CircleProgressView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 16/6/16.
 */
public class TongChengSiteCheckFragmentPresenter extends BasePresenter {

    private TongChengSiteCheckFragmentWidget tongChengSiteCheckFragmentWidget;
    private CommonPopupWindow commonPopupWindow;
    private MainActivityPresenter mainActivityPresenter;
    private ShouYeTongChengMapper shouYeTongChengMapper;
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
            tongChengSiteCheckFragmentWidget = new TongChengSiteCheckFragmentWidget(activity);
        }
        commonPopupWindow = new CommonPopupWindow();
        mainActivityPresenter = new MainActivityPresenter(activity,R.id.fly_content);
        shouYeTongChengMapper = new ShouYeTongChengMapper();

    }
    public void back(){
        mainActivityPresenter.showFragment(new TongChengFragment(activity));
    }

    public void siteCheckSubmit(){
        String area = tongChengSiteCheckFragmentWidget.tvCommonAreaSelectPlace.getText().toString();
        String rid = "zhyan";
        String appkey = "appkey";
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("area",area);
        paramMap.put("rid",rid);
        paramMap.put("appkey",appkey);
        shouYeTongChengMapper.siteCheckSubmit(paramMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //  if (!isDownRefresh)
                        //  {
                        showProgress();
                        //  }
                    }
                }).subscribe(new SubscriberCallBack<BaseModel>(new ApiCallback<BaseModel>() {
            @Override
            public void onSuccess(BaseModel model) {

            }

            @Override
            public void onFailure(int code, String msg) {
                Toast.makeText(activity,"服务器连接错误",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCompleted() {
                hideProgress();
            }
        }));
    }
    private void showProgress()
    {

        tongChengSiteCheckFragmentWidget.mCircleProgressView.setVisibility(View.VISIBLE);
        tongChengSiteCheckFragmentWidget.mCircleProgressView.spin();
        //      mRecyclerView.setVisibility(View.GONE);
    }

    public void hideProgress()
    {

        tongChengSiteCheckFragmentWidget.mCircleProgressView.setVisibility(View.GONE);
        tongChengSiteCheckFragmentWidget.mCircleProgressView.stopSpinning();
        //     mRecyclerView.setVisibility(View.VISIBLE);
    }
    public void setArea(){
        List<String> dataList = new ArrayList<String>();
        dataList.add("白象地区");
        dataList.add("柳市地区");

        commonPopupWindow.OneItemPopup(R.id.tv_common_area_select_place,activity,tongChengSiteCheckFragmentWidget.llyTongChengSiteCheckTotal,"请选择地区",dataList);
    }
    public void findNearBy(){
        mainActivityPresenter.showFragment(new CommonBaiDuMapFragment(activity));
    }
    public class TongChengSiteCheckFragmentWidget{
        public TongChengSiteCheckFragmentWidget(Activity activity){
            ButterKnife.bind(this,activity);
        }

        @Bind(R.id.lly_common_area_select)
         LinearLayout llyCommonAreaSelect;
        @Bind(R.id.tv_common_area_select_place)
         TextView tvCommonAreaSelectPlace;
        @Bind(R.id.lly_common_site_find_nearby)
         LinearLayout llyCommonSiteFindNearby;
        @Bind(R.id.lly_tongcheng_site_check_total)
         LinearLayout llyTongChengSiteCheckTotal;

        @Bind(R.id.circle_progress)
        CircleProgressView mCircleProgressView;

    }
}
