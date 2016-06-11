package com.gototongcheng.dao;

import com.gototongcheng.model.Test;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by zhyan on 16/6/11.
 */
public interface TestDao {
    /*
    测试
     */
    @POST("/json/test/test.do")
    Observable<Test> getTest();
}
