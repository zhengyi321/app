package com.gototongcheng.dao;

import com.gototongcheng.model.MainShouYeCircleModel;

import retrofit2.http.POST;
import rx.Observable;

/**
 * 首页
 * Created by zhyan on 16/6/11.
 */
public interface MainShouYeDao {

    /*
    轮播
     */
    @POST("/json/gotocityshouye/getcirclepic.do")
    Observable<MainShouYeCircleModel> getCirclePic();
}
