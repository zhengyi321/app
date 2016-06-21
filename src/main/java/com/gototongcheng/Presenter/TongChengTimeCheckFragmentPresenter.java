package com.gototongcheng.Presenter;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gototongcheng.application.R;
import com.gototongcheng.mapping.ShouYeTongChengMapper;
import com.gototongcheng.mapping.rxjava.ApiCallback;
import com.gototongcheng.mapping.rxjava.SubscriberCallBack;
import com.gototongcheng.model.BaseModel;
import com.gototongcheng.view.fragment.TongChengFragment;
import com.gototongcheng.widget.popupwindow.CommonPopupWindow;
import com.gototongcheng.widget.progressview.CircleProgressView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 16/6/16.
 */
public class TongChengTimeCheckFragmentPresenter extends BasePresenter {


    private TongChengTimeCheckFragmentWidget tongChengTimeCheckFragmentWidget;
    private CommonBaiduMapPresenter commonBaiduMapPresenter;
    private CommonPopupWindow commonPopupWindow;
    private ShouYeTongChengMapper shouYeTongChengMapper;
    public TongChengTimeCheckFragmentPresenter(){

    }
    public TongChengTimeCheckFragmentPresenter(Activity activity){
        initViews(activity);
    }

    @Override
    protected void initViews(Activity activity) {
        this.activity = activity;
        mainActivityPresenter = new MainActivityPresenter(activity, R.id.fly_content);
        if(tongChengTimeCheckFragmentWidget == null){
            tongChengTimeCheckFragmentWidget = new TongChengTimeCheckFragmentWidget(activity);
        }
        commonBaiduMapPresenter = new CommonBaiduMapPresenter(activity);
        commonPopupWindow = new CommonPopupWindow();
        shouYeTongChengMapper = new ShouYeTongChengMapper();

    }

    public void back(){
        mainActivityPresenter.showFragment(new TongChengFragment(activity));
    }


    public void setTime(){
        commonPopupWindow.DateTimeMinuteSecondPopup(R.id.tv_tongcheng_time_check_send_time,activity,tongChengTimeCheckFragmentWidget.llyTongChengTimeCheckTotal,"寄件时间");
    }
    public void sendLoc(){
        commonBaiduMapPresenter.startLoc(tongChengTimeCheckFragmentWidget.etTongChengTimeCheckSendAddress);
    }
    public void receiveLoc(){
        commonBaiduMapPresenter.startLoc(tongChengTimeCheckFragmentWidget.etTongChengTimeCheckReceiveAddress);
    }

    public void timeCheckSubmit(){
        String sendAddress = tongChengTimeCheckFragmentWidget.etTongChengTimeCheckSendAddress.getText().toString();
        String receiveAddress = tongChengTimeCheckFragmentWidget.etTongChengTimeCheckReceiveAddress.getText().toString();
        String sendTime = tongChengTimeCheckFragmentWidget.tvTongChengTimeCheckSendTime.getText().toString();
        String rid = "zhyan";
        String appkey = "appkey";
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("sendAddress",sendAddress);
        paramMap.put("receiveAddress",receiveAddress);
        paramMap.put("sendTime",sendTime);
        paramMap.put("rid",rid);
        paramMap.put("appkey",appkey);
        shouYeTongChengMapper.timeCheckSubmit(paramMap)
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

        tongChengTimeCheckFragmentWidget.mCircleProgressView.setVisibility(View.VISIBLE);
        tongChengTimeCheckFragmentWidget.mCircleProgressView.spin();
        //      mRecyclerView.setVisibility(View.GONE);
    }

    public void hideProgress()
    {

        tongChengTimeCheckFragmentWidget.mCircleProgressView.setVisibility(View.GONE);
        tongChengTimeCheckFragmentWidget.mCircleProgressView.stopSpinning();
        //     mRecyclerView.setVisibility(View.VISIBLE);
    }
    public class TongChengTimeCheckFragmentWidget{
        public TongChengTimeCheckFragmentWidget(Activity activity){
            ButterKnife.bind(this,activity);
        }

        @Bind(R.id.lly_tongcheng_time_check_total)
        LinearLayout llyTongChengTimeCheckTotal;
        @Bind(R.id.tv_tongcheng_time_check_send_time)
        TextView tvTongChengTimeCheckSendTime;
        @Bind(R.id.lly_tongcheng_time_check_send_time)
        LinearLayout llyTongChengTimeCheckSendTime;
        @Bind(R.id.et_tongcheng_time_check_receive_address)
        EditText etTongChengTimeCheckReceiveAddress;
        @Bind(R.id.lly_tongcheng_time_check_receive_address)
        LinearLayout llyTongChengTimeCheckReceiveAddress;
        @Bind(R.id.et_tongcheng_time_check_send_address)
        EditText etTongChengTimeCheckSendAddress;
        @Bind(R.id.lly_tongcheng_time_check_send_address)
        LinearLayout llyTongChengTimeCheckSendAddress;
        @Bind(R.id.circle_progress)
        CircleProgressView mCircleProgressView;
    }


}
