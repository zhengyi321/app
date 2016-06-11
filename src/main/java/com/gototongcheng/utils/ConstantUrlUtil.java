package com.gototongcheng.utils;

import java.io.Serializable;

/**
 * Created by admin on 16/6/11.
 */
public class ConstantUrlUtil implements Serializable {
    /* connect data */
    public static final int ConnTimeout = 1000 * 10;// **1000 * 30;
    public static final String NetWorkErrorMsg = "Did not work!";
    public static final String NetWorkUnknownError = "未知的网络错误!";
    public static final String NetWorkSuccess_OK = "0";
}
