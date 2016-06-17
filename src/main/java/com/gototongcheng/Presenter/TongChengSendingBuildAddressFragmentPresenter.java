package com.gototongcheng.Presenter;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gototongcheng.application.R;
import com.gototongcheng.view.fragment.TongChengSendingFragment;

/**
 * Created by admin on 16/6/16.
 */
public class TongChengSendingBuildAddressFragmentPresenter extends BasePresenter implements View.OnClickListener{

    public TongChengSendingBuildAddressFragmentWidget tongChengSendingBuildAddressFragmentWidget;
    private CommonBaiduMapPresenter commonBaiduMapPresenter;

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
            tongChengSendingBuildAddressFragmentWidget = new TongChengSendingBuildAddressFragmentWidget();

        }
        tongChengSendingBuildAddressFragmentWidget.rlyTongChengSendingBuildingGetContact = (RelativeLayout)activity.findViewById(R.id.rly_tongcheng_sending_building_get_contact);
        tongChengSendingBuildAddressFragmentWidget.etTongChengSendingBuildingName = (EditText)activity.findViewById(R.id.et_tongcheng_sending_building_name);
        tongChengSendingBuildAddressFragmentWidget.etTongChengSendingBuildingTel = (EditText)activity.findViewById(R.id.et_tongcheng_sending_building_tel);


        tongChengSendingBuildAddressFragmentWidget.rlyTongchengSendingBuildingLoc = (RelativeLayout)activity.findViewById(R.id.rly_tongcheng_sending_building_loc);
        tongChengSendingBuildAddressFragmentWidget.etTongChengSendingBuildingLoc = (EditText) activity.findViewById(R.id.et_tongcheng_sending_building_loc);
        tongChengSendingBuildAddressFragmentWidget.rlyTongchengSendingBuildingLoc.setOnClickListener(this);
        commonBaiduMapPresenter = new CommonBaiduMapPresenter(activity);
    }

    public void back(){
        mainActivityPresenter.showFragment(new TongChengSendingFragment(activity));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rly_tongcheng_sending_building_loc:
                commonBaiduMapPresenter.startLoc(tongChengSendingBuildAddressFragmentWidget.etTongChengSendingBuildingLoc);
                break;
        }
    }





    public class TongChengSendingBuildAddressFragmentWidget{

        public EditText etTongChengSendingBuildingLoc;
        public RelativeLayout rlyTongchengSendingBuildingLoc;

        public RelativeLayout rlyTongChengSendingBuildingGetContact;
        public EditText etTongChengSendingBuildingName;
        public EditText etTongChengSendingBuildingTel;
    }
}
