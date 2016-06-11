package com.gototongcheng.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.gototongcheng.model.exception.WebServiceException;

import rx.Observable;

/**
 * Created by zhyan on 16/6/11.
 */
public class BaseModel  {


    /**
     * 信息
     */
    private String message;

    /**
     * 状态码
     */
     private int statusCode;

    /**
     * 是否成功
     */
    private boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Observable filterWebServiceErrors() {
        if (true) {//judge result status is ok~~
            return Observable.just(this);
        } else {
            return Observable.error(new WebServiceException("Service return Error message"));
        }
    }


}
