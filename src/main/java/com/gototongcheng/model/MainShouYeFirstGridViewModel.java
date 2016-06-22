package com.gototongcheng.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by admin on 16/6/11.
 */
public class MainShouYeFirstGridViewModel extends BaseModel implements Parcelable {

    /*
    数据
    */
    public List<ShouYeFirstGVEntity> data;

    public List<ShouYeFirstGVEntity> getData() {
        return data;
    }

    public void setData(List<ShouYeFirstGVEntity> data) {
        this.data = data;
    }

    public class ShouYeFirstGVEntity{
        public String pic;
        public String name;
        public int id;
        public boolean isCheap;
    }



    protected MainShouYeFirstGridViewModel(Parcel in) {
        this.data = in.readArrayList(ShouYeFirstGVEntity.class.getClassLoader());
        this.setMessage(in.readString());
        this.setStatusCode(in.readInt());
        this.setSuccess(in.readByte() != 0);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
        dest.writeString(this.getMessage());
        dest.writeInt(this.getStatusCode());
        dest.writeByte((byte)((byte)(this.getSuccess() ?1:0)));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MainShouYeFirstGridViewModel> CREATOR = new Creator<MainShouYeFirstGridViewModel>() {
        @Override
        public MainShouYeFirstGridViewModel createFromParcel(Parcel in) {
            return new MainShouYeFirstGridViewModel(in);
        }

        @Override
        public MainShouYeFirstGridViewModel[] newArray(int size) {
            return new MainShouYeFirstGridViewModel[size];
        }
    };


}
