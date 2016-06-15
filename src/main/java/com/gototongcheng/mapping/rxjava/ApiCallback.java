package com.gototongcheng.mapping.rxjava;

/**
 * Created by zhyan on 16/6/15.
 */
public interface ApiCallback<T> {
    void onSuccess(T model);

    void onFailure(int code, String msg);

    void onCompleted();
}
