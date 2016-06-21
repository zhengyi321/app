package com.gototongcheng.Presenter;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gototongcheng.application.R;
import com.gototongcheng.cookies.MyIniClass;
import com.gototongcheng.mapping.ShouYeTongChengMapper;
import com.gototongcheng.mapping.rxjava.ApiCallback;
import com.gototongcheng.mapping.rxjava.SubscriberCallBack;
import com.gototongcheng.model.BaseModel;
import com.gototongcheng.model.MainShouYeFirstGridViewModel;
import com.gototongcheng.view.fragment.LoginCenterFragment;
import com.gototongcheng.view.fragment.TongChengSendingFragment;
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
public class TongChengSendingBuildAddressFragmentPresenter extends BasePresenter {

    public TongChengSendingBuildAddressFragmentWidget tongChengSendingBuildAddressFragmentWidget;
    private CommonBaiduMapPresenter commonBaiduMapPresenter;
    private ShouYeTongChengMapper shouYeTongChengMapper;
    public TongChengSendingBuildAddressFragmentPresenter(){

    }
    public TongChengSendingBuildAddressFragmentPresenter(Activity activity){
        initViews(activity);
    }

    @Override
    protected void initViews(Activity activity) {
        this.activity = activity;
        mainActivityPresenter = new MainActivityPresenter(activity, R.id.fly_content);
        if(tongChengSendingBuildAddressFragmentWidget == null){
            tongChengSendingBuildAddressFragmentWidget = new TongChengSendingBuildAddressFragmentWidget(activity);

        }
        shouYeTongChengMapper = new ShouYeTongChengMapper();
        commonBaiduMapPresenter = new CommonBaiduMapPresenter(activity);
    }

    public void back(){
        mainActivityPresenter.showFragment(new TongChengSendingFragment(activity));
    }
    public void setName(String name){
        tongChengSendingBuildAddressFragmentWidget.etTongChengSendingBuildingName.setText(name);
    }
    public void setTel(String tel){
        tongChengSendingBuildAddressFragmentWidget.etTongChengSendingBuildingTel.setText(tel);
    }

    public void setLoc(){
        commonBaiduMapPresenter.startLoc(tongChengSendingBuildAddressFragmentWidget.etTongChengSendingBuildingArea);
    }

    public void saveAddress(final int type){
        final String name = tongChengSendingBuildAddressFragmentWidget.etTongChengSendingBuildingName.getText().toString();
        final String tel = tongChengSendingBuildAddressFragmentWidget.etTongChengSendingBuildingTel.getText().toString();
        final String area = tongChengSendingBuildAddressFragmentWidget.etTongChengSendingBuildingArea.getText().toString();
        final String address = tongChengSendingBuildAddressFragmentWidget.etTongChengSendingBuildingAddress.getText().toString();
        String rid = "zhyan";
        String appkey = "test";
        Boolean isdefault ;
        if(tongChengSendingBuildAddressFragmentWidget.cbTongChengSendingBuildingIsdefault.isChecked()){
            isdefault = true;
        }else{
            isdefault = false;
        }
        final Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("name",name);
        paramMap.put("tel",tel);
        paramMap.put("area",area);
        paramMap.put("address",address);
        paramMap.put("isdefault",""+isdefault);
        paramMap.put("rid",rid);
        paramMap.put("appkey",appkey);
        if(rid.isEmpty() || (rid == null)){
            mainActivityPresenter.showFragment(new LoginCenterFragment(activity));
        }

        shouYeTongChengMapper.addressSave(paramMap).subscribeOn(Schedulers.io())
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
                dataSaveIni(type,paramMap);
                Toast.makeText(activity,"保存成功"+model.getStatusCode(),Toast.LENGTH_LONG).show();
                mainActivityPresenter.showFragment(new TongChengSendingFragment(activity));
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

    private void dataSaveIni(int type,Map<String,String> paramMap){
        MyIniClass myIniClass = new MyIniClass(activity,"");

        switch (type){
            case 0:
                if(paramMap.get("isdefault").equals("true")){;

                    myIniClass.WriteString("receiveData",paramMap.get("name")+" "+paramMap.get("tel")+" "+paramMap.get("area")+" "+paramMap.get("address"));
                }
                myIniClass.WriteString("receiveDataTemp",paramMap.get("name")+" "+paramMap.get("tel")+" "+paramMap.get("area")+" "+paramMap.get("address"));

                myIniClass.WriteBoolean("isreceiveback",true);
                break;
            case 1:
                if(paramMap.get("isdefault").equals("true")){
                    myIniClass.WriteString("sendData",paramMap.get("name")+" "+paramMap.get("tel")+" "+paramMap.get("area")+" "+paramMap.get("address"));
                }
                myIniClass.WriteString("sendDataTemp",paramMap.get("name")+" "+paramMap.get("tel")+" "+paramMap.get("area")+" "+paramMap.get("address"));
                myIniClass.WriteBoolean("issendback",true);
                break;
        }
    }
    private void showProgress()
    {

        tongChengSendingBuildAddressFragmentWidget.mCircleProgressView.setVisibility(View.VISIBLE);
        tongChengSendingBuildAddressFragmentWidget.mCircleProgressView.spin();
        //      mRecyclerView.setVisibility(View.GONE);
    }

    public void hideProgress()
    {

        tongChengSendingBuildAddressFragmentWidget.mCircleProgressView.setVisibility(View.GONE);
        tongChengSendingBuildAddressFragmentWidget.mCircleProgressView.stopSpinning();
        //     mRecyclerView.setVisibility(View.VISIBLE);
    }
    public void changeWordColorByCheckBox(){
        if(tongChengSendingBuildAddressFragmentWidget.cbTongChengSendingBuildingIsdefault.isChecked()){
            tongChengSendingBuildAddressFragmentWidget.cbTongChengSendingBuildingIsdefault.setTextColor(activity.getResources().getColor(R.color.color_tongcheng_rang_check_submit_deep_green_bg));
        }else{
            tongChengSendingBuildAddressFragmentWidget.cbTongChengSendingBuildingIsdefault.setTextColor(activity.getResources().getColor(R.color.white));
        }
    }

    public class TongChengSendingBuildAddressFragmentWidget{
        public TongChengSendingBuildAddressFragmentWidget(Activity activity){
            ButterKnife.bind(this,activity);
        }
        @Bind(R.id.et_tongcheng_sending_building_name)
        EditText etTongChengSendingBuildingName;
        @Bind(R.id.et_tongcheng_sending_building_tel)
        EditText etTongChengSendingBuildingTel;
        @Bind(R.id.et_tongcheng_sending_building_area)
        EditText etTongChengSendingBuildingArea;
        @Bind(R.id.et_tongcheng_sending_building_address)
        EditText etTongChengSendingBuildingAddress;
        @Bind(R.id.cb_tongcheng_sending_building_isdefault)
        CheckBox cbTongChengSendingBuildingIsdefault;
        @Bind(R.id.circle_progress)
        CircleProgressView mCircleProgressView;
    }


}
