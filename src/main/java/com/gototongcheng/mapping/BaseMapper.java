package com.gototongcheng.mapping;

import android.text.TextUtils;

import com.gototongcheng.application.GotoCityApp;
import com.gototongcheng.utils.LogUtil;
import com.gototongcheng.utils.NetWorkUtil;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by zhyan on 16/6/11.
 */
public class BaseMapper {
    public static final String TEST_URL = "http://192.168.10.198:8080";

    public static  OkHttpClient mOkHttpClient;


    public static final int CACHE_TIME_LONG = 60 * 60 * 24 * 7;

    /**
     * 初始化OKHttpClient
     */
    protected void initOkHttpClient()
    {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null)
        {
            synchronized (BaseMapper.class)
            {
                if (mOkHttpClient == null)
                {
                    //设置Http缓存
                    Cache cache = new Cache(new File(GotoCityApp.getContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);

                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(interceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .build();


                }
            }
        }
    }

    private Interceptor mRewriteCacheControlInterceptor = new Interceptor()
    {

        @Override
        public Response intercept(Chain chain) throws IOException
        {

            Request request = chain.request();
            if (!NetWorkUtil.isNetworkConnected()) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetWorkUtil.isNetworkConnected()) {
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder().header("Cache-Control", cacheControl).removeHeader("Pragma").build();
            } else {
                return originalResponse.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_TIME_LONG)
                        .removeHeader("Pragma").build();
            }

        }
    };

}
