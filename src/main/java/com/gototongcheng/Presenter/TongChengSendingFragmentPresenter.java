package com.gototongcheng.Presenter;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gototongcheng.application.R;
import com.gototongcheng.cookies.MyIniClass;
import com.gototongcheng.mapping.MainShouYeMapper;
import com.gototongcheng.mapping.ShouYeTongChengMapper;
import com.gototongcheng.mapping.rxjava.ApiCallback;
import com.gototongcheng.mapping.rxjava.SubscriberCallBack;
import com.gototongcheng.model.BaseModel;
import com.gototongcheng.view.fragment.TongChengFragment;
import com.gototongcheng.view.fragment.TongChengSendingFragment;
import com.gototongcheng.widget.popupwindow.CommonPopupWindow;
import com.gototongcheng.widget.progressview.CircleProgressView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhyan on 16/6/16.
 */
public class TongChengSendingFragmentPresenter extends BasePresenter {


    public TongChengSendingWidget tongChengSendingWidget ;
    public CommonPopupWindow commonPopupWindow;
    private ShouYeTongChengMapper shouYeTongChengMapper;
    public TongChengSendingFragmentPresenter(){

    }
    public TongChengSendingFragmentPresenter(Activity activity){

        initViews(activity);
    }

    protected void initViews(Activity activity) {
        this.activity = activity;
        mainActivityPresenter = new MainActivityPresenter(activity, R.id.fly_content);
        commonPopupWindow = new CommonPopupWindow();
        if(tongChengSendingWidget == null) {
            tongChengSendingWidget = new TongChengSendingWidget(activity);
        }
        initSendReceiveData();
        shouYeTongChengMapper = new ShouYeTongChengMapper();

    }
    private void initSendReceiveData(){
        MyIniClass myIniClass = new MyIniClass(activity,"");
        String receiveData = myIniClass.ReadString("receiveData","");
        String receiveDataTemp = myIniClass.ReadString("receiveDataTemp","");
        String sendData = myIniClass.ReadString("sendData","");
        String sendDataTemp = myIniClass.ReadString("sendDataTemp","");
        Boolean isreceiveback = myIniClass.Readbool("isreceiveback",false);
        Boolean issendback = myIniClass.Readbool("issendback",false);
        if(isreceiveback == true){
            setAddress(0,receiveDataTemp);
            myIniClass.WriteBoolean("isreceiveback",false);
        }else{
            setAddress(0,receiveData);
        }
        if(issendback == true){
            setAddress(1,sendDataTemp);
            myIniClass.WriteBoolean("issendback",false);
        }else{
            setAddress(1,sendData);
        }

    }


    public void back() {
        mainActivityPresenter.showFragment(new TongChengFragment(activity));
    }

    public void submit(){
        String sendData = tongChengSendingWidget.tvTongChengSendingSenderAddress.getText().toString();
        String receiveData = tongChengSendingWidget.tvTongChengSendingReceiveAddress.getText().toString();
        String weight = tongChengSendingWidget.etTongChengSendingWeight.getText().toString();
        String time = tongChengSendingWidget.tvTongChengSendingTimeTo.getText().toString();
        int payType = 0;
        if(tongChengSendingWidget.rbTongChengSendingSpay.isChecked()){
            payType = 1;
        }else if(tongChengSendingWidget.rbTongChengSendingRpay.isChecked()){
            payType = 2;
        }else if(tongChengSendingWidget.rbTongChengSendingMonthBalance.isChecked()){
            payType = 3;
        }else if(tongChengSendingWidget.rbTongChengSendingRecharge.isChecked()){
            payType = 4;
        }
        String rid = "zhyan";
        String appkey = "appkey";
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("sendData",sendData);
        paramMap.put("receiveData",receiveData);
        paramMap.put("weight",weight);
        paramMap.put("time",time);
        paramMap.put("payType",payType);
        paramMap.put("rid",rid);
        paramMap.put("appkey",appkey);
        shouYeTongChengMapper.sendDataSubmit(paramMap)
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
                })
                .cache()
                .subscribe(new SubscriberCallBack<BaseModel>(new ApiCallback<BaseModel>() {
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

        tongChengSendingWidget.mCircleProgressView.setVisibility(View.VISIBLE);
        tongChengSendingWidget.mCircleProgressView.spin();
        //      mRecyclerView.setVisibility(View.GONE);
    }

    public void hideProgress()
    {

        tongChengSendingWidget.mCircleProgressView.setVisibility(View.GONE);
        tongChengSendingWidget.mCircleProgressView.stopSpinning();
        //     mRecyclerView.setVisibility(View.VISIBLE);
    }

    public void setAddress(int type,String address){
    //    Toast.makeText(activity,address,Toast.LENGTH_LONG).show();
        switch (type){
            case 0:
                tongChengSendingWidget.tvTongChengSendingReceiveAddress.setText(address);
                break;
            case 1:
                tongChengSendingWidget.tvTongChengSendingSenderAddress.setText(address);
                break;

            default:
                break;

        }
    }

    public void timeToGet(){
        commonPopupWindow.DateTimeMinuteSecondPopup(R.id.tv_tongcheng_sending_time_to,activity,tongChengSendingWidget.llyTongChengSendingTotal,"上门时间");
    }
    public void radioManage(int type){
        switch (type){
            case 0:

                tongChengSendingWidget.rbTongChengSendingSpay.setChecked(true);
                tongChengSendingWidget.rbTongChengSendingRpay.setChecked(false);
                tongChengSendingWidget.rbTongChengSendingMonthBalance.setChecked(false);
                tongChengSendingWidget.rbTongChengSendingRecharge.setChecked(false);
                tongChengSendingWidget.tvTongChengSendingSpay.setBackgroundResource(R.drawable.shape_yellow_circle_radius);
                tongChengSendingWidget.tvTongChengSendingRpay.setBackgroundResource(R.drawable.shape_gray_circle_radius);
                tongChengSendingWidget.tvTongChengSendingMonthBalance.setBackgroundResource(R.drawable.shape_gray_circle_radius);
                tongChengSendingWidget.tvTongChengSendingRecharge.setBackgroundResource(R.drawable.shape_gray_circle_radius);
                break;
            case 1:
                tongChengSendingWidget.rbTongChengSendingSpay.setChecked(false);
                tongChengSendingWidget.rbTongChengSendingRpay.setChecked(true);
                tongChengSendingWidget.rbTongChengSendingMonthBalance.setChecked(false);
                tongChengSendingWidget.rbTongChengSendingRecharge.setChecked(false);
                tongChengSendingWidget.tvTongChengSendingSpay.setBackgroundResource(R.drawable.shape_gray_circle_radius);
                tongChengSendingWidget.tvTongChengSendingRpay.setBackgroundResource(R.drawable.shape_yellow_circle_radius);
                tongChengSendingWidget.tvTongChengSendingMonthBalance.setBackgroundResource(R.drawable.shape_gray_circle_radius);
                tongChengSendingWidget.tvTongChengSendingRecharge.setBackgroundResource(R.drawable.shape_gray_circle_radius);
                break;

            case 2:
                tongChengSendingWidget.rbTongChengSendingSpay.setChecked(false);
                tongChengSendingWidget.rbTongChengSendingRpay.setChecked(false);
                tongChengSendingWidget.rbTongChengSendingMonthBalance.setChecked(true);
                tongChengSendingWidget.rbTongChengSendingRecharge.setChecked(false);
                tongChengSendingWidget.tvTongChengSendingSpay.setBackgroundResource(R.drawable.shape_gray_circle_radius);
                tongChengSendingWidget.tvTongChengSendingRpay.setBackgroundResource(R.drawable.shape_gray_circle_radius);
                tongChengSendingWidget.tvTongChengSendingMonthBalance.setBackgroundResource(R.drawable.shape_yellow_circle_radius);
                tongChengSendingWidget.tvTongChengSendingRecharge.setBackgroundResource(R.drawable.shape_gray_circle_radius);
                break;

            case 3:
                tongChengSendingWidget.rbTongChengSendingSpay.setChecked(false);
                tongChengSendingWidget.rbTongChengSendingRpay.setChecked(false);
                tongChengSendingWidget.rbTongChengSendingMonthBalance.setChecked(false);
                tongChengSendingWidget.rbTongChengSendingRecharge.setChecked(true);
                tongChengSendingWidget.tvTongChengSendingSpay.setBackgroundResource(R.drawable.shape_gray_circle_radius);
                tongChengSendingWidget.tvTongChengSendingRpay.setBackgroundResource(R.drawable.shape_gray_circle_radius);
                tongChengSendingWidget.tvTongChengSendingMonthBalance.setBackgroundResource(R.drawable.shape_gray_circle_radius);
                tongChengSendingWidget.tvTongChengSendingRecharge.setBackgroundResource(R.drawable.shape_yellow_circle_radius);
                break;
        }
    }
    public  class TongChengSendingWidget{
        public TongChengSendingWidget(Activity activity){
            ButterKnife.bind(this,activity);
        }

        @Bind(R.id.lly_tongcheng_sending_total)
        LinearLayout llyTongChengSendingTotal;
        @Bind(R.id.lly_tongcheng_sending_sender)
        LinearLayout llyTongchengSendingSender;
        @Bind(R.id.lly_tongcheng_sending_receiver)
        LinearLayout llyTongchengSendingReceiver;


        //付款方式
        @Bind(R.id.lly_tongcheng_sending_spay)
        LinearLayout llyTongChengSendingSpay;
        @Bind(R.id.lly_tongcheng_sending_rpay)
        LinearLayout llyTongChengSendingRpay;
        @Bind(R.id.lly_tongcheng_sending_month_balance)
        LinearLayout llyTongChengSendingMonthBalance;
        @Bind(R.id.lly_tongcheng_sending_recharge)
        LinearLayout llyTongChengSendingRecharge;

        @Bind(R.id.tv_tongcheng_sending_spay)
        TextView tvTongChengSendingSpay;
        @Bind(R.id.tv_tongcheng_sending_rpay)
        TextView tvTongChengSendingRpay;
        @Bind(R.id.tv_tongcheng_sending_month_balance)
        TextView tvTongChengSendingMonthBalance;
        @Bind(R.id.tv_tongcheng_sending_recharge)
        TextView tvTongChengSendingRecharge;

        @Bind(R.id.rg_tongcheng_sending_paymethod)
        RadioGroup rgTongChengSendingPayMethod;
        @Bind(R.id.rb_tongcheng_sending_spay)
        RadioButton rbTongChengSendingSpay;
        @Bind(R.id.rb_tongcheng_sending_rpay)
        RadioButton rbTongChengSendingRpay;
        @Bind(R.id.rb_tongcheng_sending_month_balance)
        RadioButton rbTongChengSendingMonthBalance;
        @Bind(R.id.rb_tongcheng_sending_recharge)
        RadioButton rbTongChengSendingRecharge;
        //付款方式

        //上门时间
        @Bind(R.id.lly_tongcheng_sending_time_to)
        LinearLayout llyTongChengSendingTimeTo;
        @Bind(R.id.tv_tongcheng_sending_time_to)
        TextView tvTongChengSendingTimeTo;
        //上门时间
        //寄收件地址
        @Bind(R.id.tv_tongcheng_sending_sender_address)
        TextView tvTongChengSendingSenderAddress;
        @Bind(R.id.tv_tongcheng_sending_receiver_address)
        TextView tvTongChengSendingReceiveAddress;

        //寄收件地址
        //重量
        @Bind(R.id.et_tongcheng_sending_weight)
        EditText etTongChengSendingWeight;

        //加载控件
        @Bind(R.id.circle_progress)
        CircleProgressView mCircleProgressView;
    }

}
