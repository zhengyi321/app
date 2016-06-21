package com.gototongcheng.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

/**
 * Created by admin on 16/6/20.
 */
public class TongChengSendingModel extends BaseModel implements Parcelable{

    /*
    数据
    */
    public List<SendingEntity> data;

    public List<SendingEntity> getData() {
        return data;
    }

    public void setData(List<SendingEntity> data) {
        this.data = data;
    }

    public class SendingEntity{

        public String receiveAddress;

        public String sendAddress;

        public String weight;

        public Date times;

        public int state;//0为寄件地址，1为收件地址

        public boolean isdefault;  //是否为默认寄件地址

    }

    protected TongChengSendingModel(Parcel in) {
        this.data = in.readArrayList(SendingEntity.class.getClassLoader());
        this.setMessage(in.readString());
        this.setStatusCode(in.readInt());
        this.setSuccess(in.readByte() != 0);
    }

    public static final Creator<TongChengSendingModel> CREATOR = new Creator<TongChengSendingModel>() {
        @Override
        public TongChengSendingModel createFromParcel(Parcel in) {
            return new TongChengSendingModel(in);
        }

        @Override
        public TongChengSendingModel[] newArray(int size) {
            return new TongChengSendingModel[size];
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
