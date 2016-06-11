package com.gototongcheng.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.gototongcheng.application.GotoCityApp;

/**
 * 网络工具类
 */
public class NetWorkUtil
{

    private NetWorkUtil()
    {

    }

    public static boolean isNetworkConnected()
    {

        if (GotoCityApp.getContext() != null)
        {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) GotoCityApp.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null)
            {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static boolean isWifiConnected()
    {

        if (GotoCityApp.getContext() != null)
        {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) GotoCityApp.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null)
            {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static boolean isMobileConnected()
    {

        if (GotoCityApp.getContext() != null)
        {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) GotoCityApp.getContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null)
            {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static int getConnectedType()
    {

        if (GotoCityApp.getContext() != null)
        {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) GotoCityApp.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable())
            {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }
    /**
     *
     * @param Msg
     * @param currContext
     */
    public static void ShowMessage(String Msg, Context currContext) {
        if (Msg.isEmpty()) {
            Msg = ConstantUrlUtil.NetWorkUnknownError;
        }
        Toast.makeText(currContext, Msg, Toast.LENGTH_LONG).show();
    }
    /**
     * showDialog
     * @param currContext
     * @param Caption
     * @param Msg
     */
    public static void ShowDialog(Context currContext, String Caption,
                                  String Msg) {
        new AlertDialog.Builder(currContext).setTitle(Caption).setMessage(Msg)
                .setPositiveButton("完成", null).show();
    }
}
