package com.gototongcheng.dao;

import com.gototongcheng.model.CommonCircleModel;
import com.gototongcheng.model.MainShouYeFirstGridViewModel;

import retrofit2.http.GET;
import rx.Observable;

/**
 * 首页
 * Created by zhyan on 16/6/11.
 */
public interface MainShouYeDao {

    /*
    轮播
     */
    @GET("/json/gotocityshouye/getcirclepic.do")
    Observable<CommonCircleModel> getCirclePic();

    /*
    首页第一个gridview
     */
    @GET("/json/gotocityshouye/getfirstgridview.do")
    Observable<MainShouYeFirstGridViewModel> getMainShouYeFirstGridView();
}
