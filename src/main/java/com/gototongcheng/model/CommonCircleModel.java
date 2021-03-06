package com.gototongcheng.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 首页轮播
 * Created by zhyan on 16/6/10.
 */
public class CommonCircleModel extends BaseModel implements Parcelable {
    /*
        数据
    */
    public List<CircleEntity> data;

    public List<CircleEntity> getData() {
        return data;
    }

    public void setData(List<CircleEntity> data) {
        this.data = data;
    }

    public class CircleEntity{
        public String pic;
        public int id;
    }

    public CommonCircleModel(){

    }

    protected CommonCircleModel(Parcel in) {
        this.data = in.readArrayList(CircleEntity.class.getClassLoader());
        this.setMessage(in.readString());
        this.setStatusCode(in.readInt());
        this.setSuccess(in.readByte() != 0);
    }

    public static final Creator<CommonCircleModel> CREATOR = new Creator<CommonCircleModel>() {
        @Override
        public CommonCircleModel createFromParcel(Parcel in) {
            return new CommonCircleModel(in);
        }

        @Override
        public CommonCircleModel[] newArray(int size) {
            return new CommonCircleModel[size];
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
