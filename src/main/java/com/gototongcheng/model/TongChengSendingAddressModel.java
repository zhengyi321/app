package com.gototongcheng.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 *
 *  新建寄件收件地址
 * Created by zhyan on 16/6/20.
 */
public class TongChengSendingAddressModel extends BaseModel implements Parcelable {

    /*
    数据
    */
    public List<AddressEntity> data;

    public List<AddressEntity> getData() {
        return data;
    }

    public void setData(List<AddressEntity> data) {
        this.data = data;
    }

    public class AddressEntity{

        public String name;

        public String tel;

        public String area;

        public String address;

        public int state;//0为寄件地址，1为收件地址

        public boolean isdefault;  //是否为默认寄件地址

    }

    protected TongChengSendingAddressModel(Parcel in) {

        this.data = in.readArrayList(AddressEntity.class.getClassLoader());
        this.setMessage(in.readString());
        this.setStatusCode(in.readInt());
        this.setSuccess(in.readByte() != 0);
    }

    public static final Creator<TongChengSendingAddressModel> CREATOR = new Creator<TongChengSendingAddressModel>() {
        @Override
        public TongChengSendingAddressModel createFromParcel(Parcel in) {
            return new TongChengSendingAddressModel(in);
        }

        @Override
        public TongChengSendingAddressModel[] newArray(int size) {
            return new TongChengSendingAddressModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
        dest.writeString(this.getMessage());
        dest.writeInt(this.getStatusCode());
        dest.writeByte((byte)((byte)(this.getSuccess() ?1:0)));
    }
}
