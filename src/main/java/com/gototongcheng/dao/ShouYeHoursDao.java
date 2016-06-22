package com.gototongcheng.dao;

import com.gototongcheng.model.CommonCircleModel;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by zhyan on 16/6/22.
 */
public interface ShouYeHoursDao {
    /*
    轮播
     */
    @GET("/json/shouyehours/getcirclepic.do")
    Observable<CommonCircleModel> getCirclePic();


    /*
    第一个gridview
     */
}
