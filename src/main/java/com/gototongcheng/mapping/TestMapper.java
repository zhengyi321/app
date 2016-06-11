package com.gototongcheng.mapping;

import com.gototongcheng.dao.TestDao;
import com.gototongcheng.model.Test;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by zhyan on 16/6/11.
 */
public class TestMapper extends BaseMapper {
    private TestDao testDao;
    public TestMapper(){
        initOkHttpClient();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(TEST_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        testDao = mRetrofit.create(TestDao.class);
    }

    public Observable<Test> getTest()
    {
        return testDao.getTest();
    }
}
