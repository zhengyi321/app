package com.gototongcheng.dao;

import com.gototongcheng.model.BaseModel;
import com.gototongcheng.model.CommonCircleModel;
import com.gototongcheng.model.TongChengSendingAddressModel;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by admin on 16/6/20.
 */
public interface ShouYeTongChengDao {

    /*
    轮播
     */
    @GET("/json/shouyetongcheng/getcirclepic.do")
    Observable<CommonCircleModel> getCirclePic();

    /*
    寄收件地址保存
     */
    @POST("/json/shouyetongcheng/addresssave.do")
    Observable<BaseModel> addressSave(@QueryMap Map<String,String> paramMap);

    /*
    获取寄收件默认地址
     */
    @POST("/json/shouyetongcheng/addressget.do")
    Observable<TongChengSendingAddressModel> addressGet(@Query("rid")String rid,@Query("appkey")String appkey);

    /*
    寄件提交
     */
    @POST("/json/shouyetongcheng/senddatasubmit.do")
    Observable<BaseModel> sendDataSubmit(@QueryMap Map<String,Object> paramMap);

    /*
    运费查询
     */
    @POST("/json/shouyetongcheng/feecheck.do")
    Observable<BaseModel> feeCheckSubmit(@QueryMap Map<String,Object> paramMap);

    /*
    时效查询
     */
    @POST("/json/shouyetongcheng/timecheck.do")
    Observable<BaseModel> timeCheckSubmit(@QueryMap Map<String,Object> paramMap);

    /*
    网点查询
     */
    @POST("/json/shouyetongcheng/sitecheck.do")
    Observable<BaseModel> siteCheckSubmit(@QueryMap Map<String,Object> paramMap);

    /*
    范围查询
     */
    @POST("/json/shouyetongcheng/rangcheck.do")
    Observable<BaseModel> rangCheckSubmit(@QueryMap Map<String,Object> paramMap);

}
