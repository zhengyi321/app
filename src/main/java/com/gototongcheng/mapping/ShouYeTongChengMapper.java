package com.gototongcheng.mapping;

import com.gototongcheng.dao.ShouYeTongChengDao;
import com.gototongcheng.model.BaseModel;
import com.gototongcheng.model.CommonCircleModel;
import com.gototongcheng.model.TongChengSendingAddressModel;

import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zhyan on 16/6/20.
 */
public class ShouYeTongChengMapper extends BaseMapper {
    private ShouYeTongChengDao shouYeTongChengDao;

    public ShouYeTongChengMapper(){
        initOkHttpClient();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(TEST_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        shouYeTongChengDao = mRetrofit.create(ShouYeTongChengDao.class);
    }


    public Observable<CommonCircleModel> getCirclePic(){
        return shouYeTongChengDao.getCirclePic();
    };

    public Observable<BaseModel> addressSave(Map<String,String> paramMap){
        return shouYeTongChengDao.addressSave(paramMap);
    }
    public Observable<TongChengSendingAddressModel> addressGet(String rid,String appkey){
        return shouYeTongChengDao.addressGet(rid,appkey);
    };
    public Observable<BaseModel> sendDataSubmit(Map<String,Object> paramMap){
        return shouYeTongChengDao.sendDataSubmit(paramMap);
    };
    public Observable<BaseModel> feeCheckSubmit(Map<String,Object> paramMap){
        return shouYeTongChengDao.feeCheckSubmit(paramMap);
    };
    public Observable<BaseModel> timeCheckSubmit(Map<String,Object> paramMap){
        return shouYeTongChengDao.timeCheckSubmit(paramMap);
    };
    public Observable<BaseModel> siteCheckSubmit(Map<String,Object> paramMap){
        return shouYeTongChengDao.siteCheckSubmit(paramMap);
    };
    public  Observable<BaseModel> rangCheckSubmit(Map<String,Object> paramMap){
        return shouYeTongChengDao.rangCheckSubmit(paramMap);
    };
}
