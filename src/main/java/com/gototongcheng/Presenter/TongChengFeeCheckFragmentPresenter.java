package com.gototongcheng.Presenter;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gototongcheng.application.R;
import com.gototongcheng.mapping.ShouYeTongChengMapper;
import com.gototongcheng.mapping.rxjava.ApiCallback;
import com.gototongcheng.mapping.rxjava.SubscriberCallBack;
import com.gototongcheng.model.BaseModel;
import com.gototongcheng.view.fragment.TongChengFragment;
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
public class TongChengFeeCheckFragmentPresenter extends BasePresenter {

    private TongChengFeeCheckWidget tongChengFeeCheckWidget;
    private CommonBaiduMapPresenter commonBaiduMapPresenter;
    private ShouYeTongChengMapper shouYeTongChengMapper;
    public TongChengFeeCheckFragmentPresenter(){

    }
    public TongChengFeeCheckFragmentPresenter(Activity activity){
        initViews(activity);
    }

    @Override
    protected void initViews(Activity activity) {
        this.activity = activity;
        shouYeTongChengMapper = new ShouYeTongChengMapper();
        mainActivityPresenter = new MainActivityPresenter(activity, R.id.fly_content);
        commonBaiduMapPresenter = new CommonBaiduMapPresenter(activity);
        if(tongChengFeeCheckWidget == null) {
            tongChengFeeCheckWidget = new TongChengFeeCheckWidget(activity);
        }
    }

    public void back(){
        mainActivityPresenter.showFragment(new TongChengFragment(activity));
    }

    public void checkSubmit(){
        String sendAddress = tongChengFeeCheckWidget.etTongchengFeeCheckSendAddress.getText().toString();
        String receiveAddress = tongChengFeeCheckWidget.etTongchengFeeCheckReceiveAddress.getText().toString();
        String weight = tongChengFeeCheckWidget.etTongChengFeeCheckWeight.getText().toString();
        String volumn = tongChengFeeCheckWidget.etTongChengFeeCheckVolumn.getText().toString();
        String rid = "zhyan";
        String appkey = "appkey";
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("sendAddress",sendAddress);
        paramMap.put("receiveAddress",receiveAddress);
        paramMap.put("weight",weight);
        paramMap.put("volumn",volumn);
        paramMap.put("rid",rid);
        paramMap.put("appkey",appkey);
        shouYeTongChengMapper.feeCheckSubmit(paramMap)
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

        tongChengFeeCheckWidget.mCircleProgressView.setVisibility(View.VISIBLE);
        tongChengFeeCheckWidget.mCircleProgressView.spin();
        //      mRecyclerView.setVisibility(View.GONE);
    }

    public void hideProgress()
    {

        tongChengFeeCheckWidget.mCircleProgressView.setVisibility(View.GONE);
        tongChengFeeCheckWidget.mCircleProgressView.stopSpinning();
        //     mRecyclerView.setVisibility(View.VISIBLE);
    }


    public void receiveLoc(){
        commonBaiduMapPresenter.startLoc(tongChengFeeCheckWidget.etTongchengFeeCheckReceiveAddress);
    }
    public void sendLoc(){
        commonBaiduMapPresenter.startLoc(tongChengFeeCheckWidget.etTongchengFeeCheckSendAddress);
    }
    public class TongChengFeeCheckWidget{
        public TongChengFeeCheckWidget(Activity activity){
            ButterKnife.bind(this,activity);
        }
        @Bind(R.id.et_tongcheng_fee_check_receive_address)
        EditText etTongchengFeeCheckReceiveAddress;
        @Bind(R.id.lly_tongcheng_fee_check_receive_address)
        LinearLayout llyTongchengFeeCheckReceiveAddress;

        @Bind(R.id.et_tongcheng_fee_check_send_address)
        EditText etTongchengFeeCheckSendAddress;
        @Bind(R.id.lly_tongcheng_fee_check_send_address)
        LinearLayout llyTongchengFeeCheckSendAddress;


        @Bind(R.id.et_tongcheng_fee_check_weight)
        EditText etTongChengFeeCheckWeight;
        @Bind(R.id.et_tongcheng_fee_check_volumn)
        EditText etTongChengFeeCheckVolumn;

        @Bind(R.id.circle_progress)
        CircleProgressView mCircleProgressView;
    }
}
