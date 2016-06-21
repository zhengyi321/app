package com.gototongcheng.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.TongChengRangCheckFragmentPresenter;
import com.gototongcheng.Presenter.TongChengSendingBuildAddressFragmentPresenter;
import com.gototongcheng.application.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        tongChengSendingBuildAddressFragmentPresenter = new TongChengSendingBuildAddressFragmentPresenter(activity);

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
                tongChengSendingBuildAddressFragmentPresenter.setName(username);
                tongChengSendingBuildAddressFragmentPresenter.setTel(usernumber);
         //       text.setText(usernumber+" ("+username+")");
            }

        }
    }

    @OnClick(R.id.rly_left)
    public void backOnclick(){
        tongChengSendingBuildAddressFragmentPresenter.back();
    }

    @OnClick(R.id.rly_tongcheng_sending_building_loc)
    public void locOnclick(){
        tongChengSendingBuildAddressFragmentPresenter.setLoc();
    }
    @OnClick(R.id.rly_tongcheng_sending_building_get_contact)
    public void contactOnclick(){
        startActivityForResult(new Intent(
                Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI), 0);
    }
    @OnClick(R.id.cb_tongcheng_sending_building_isdefault)
    public void isDefaultOnclick(){
        tongChengSendingBuildAddressFragmentPresenter.changeWordColorByCheckBox();
    }
    @OnClick(R.id.rly_right)
    public void saveOnclick(){
        tongChengSendingBuildAddressFragmentPresenter.saveAddress(type);
    }
}
