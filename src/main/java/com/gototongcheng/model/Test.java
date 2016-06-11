package com.gototongcheng.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by zhyan on 16/6/11.
 */
public class Test extends BaseModel implements Parcelable {
    /*
 数据
  */
    public List<TestEntity> data;

    public List<TestEntity> getData() {
        return data;
    }

    public void setData(List<TestEntity> data) {
        this.data = data;
    }

    public class TestEntity{
        public String test;
    }

    protected Test(Parcel in) {
        this.data = in.readArrayList(TestEntity.class.getClassLoader());
        this.setMessage(in.readString());
        this.setStatusCode(in.readInt());
        this.setSuccess(in.readByte() != 0);
    }

    public static final Creator<Test> CREATOR = new Creator<Test>() {
        @Override
        public Test createFromParcel(Parcel in) {
            return new Test(in);
        }

        @Override
        public Test[] newArray(int size) {
            return new Test[size];
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
