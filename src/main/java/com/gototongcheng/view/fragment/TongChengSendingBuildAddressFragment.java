package com.gototongcheng.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.View;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.TongChengRangCheckFragmentPresenter;
import com.gototongcheng.Presenter.TongChengSendingBuildAddressFragmentPresenter;
import com.gototongcheng.application.R;

/**
 * 新建收寄件地址
 * Created by zhyan on 16/6/16.
 */
public class TongChengSendingBuildAddressFragment extends BaseFragment {

    private int type;//0为收件地址 1为寄件地址
    private CommonTopBarPresenter commonTopBarPresenter;
    private TongChengSendingBuildAddressFragmentPresenter tongChengSendingBuildAddressFragmentPresenter;
    private String username,usernumber;
    public TongChengSendingBuildAddressFragment(){

    }
    @SuppressLint("ValidFragment")
    public TongChengSendingBuildAddressFragment(Activity activity,String type){
        this.activity = activity;
        switch (type){
            case "receive":
                this.type = 0;
                break;
            case "send":
                this.type = 1;
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_tongcheng_sending_build_address_sv;
    }

    @Override
    public void initViews() {
        commonTopBarPresenter = new CommonTopBarPresenter(activity);
        commonTopBarPresenter.topBarCommonWidget.rlyLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tongChengSendingBuildAddressFragmentPresenter.back();
            }
        });
        tongChengSendingBuildAddressFragmentPresenter = new TongChengSendingBuildAddressFragmentPresenter(activity);
        tongChengSendingBuildAddressFragmentPresenter.tongChengSendingBuildAddressFragmentWidget.rlyTongChengSendingBuildingGetContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(
                        Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI), 0);
            }
        });
    }

    @Override
    public void initToolBar() {
        switch (type){
            case 0:
                commonTopBarPresenter.initTopBar("tongchengsendingbuildreceiveaddress");
            break;
            case 1:
                commonTopBarPresenter.initTopBar("tongchengsendingbuildsendaddress");
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            ContentResolver reContentResolverol = activity.getContentResolver();
            Uri contactData = data.getData();
            @SuppressWarnings("deprecation")
            Cursor cursor = activity.managedQuery(contactData, null, null, null, null);
            cursor.moveToFirst();
            username = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = reContentResolverol.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                    null,
                    null);
            while (phone.moveToNext()) {
                usernumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                tongChengSendingBuildAddressFragmentPresenter.tongChengSendingBuildAddressFragmentWidget.etTongChengSendingBuildingName.setText(username);
                tongChengSendingBuildAddressFragmentPresenter.tongChengSendingBuildAddressFragmentWidget.etTongChengSendingBuildingTel.setText(usernumber);
         //       text.setText(usernumber+" ("+username+")");
            }

        }
    }


}
