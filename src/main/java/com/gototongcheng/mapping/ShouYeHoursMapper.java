package com.gototongcheng.mapping;

import com.gototongcheng.dao.ShouYeHoursDao;
import com.gototongcheng.dao.ShouYeTongChengDao;
import com.gototongcheng.model.CommonCircleModel;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by admin on 16/6/22.
 */
public class ShouYeHoursMapper extends BaseMapper {
    private ShouYeHoursDao shouYeHoursDao;
    public ShouYeHoursMapper(){
        initOkHttpClient();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(TEST_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        shouYeHoursDao = mRetrofit.create(ShouYeHoursDao.class);
    }
    public Observable<CommonCircleModel> getCirclePic(){
        return shouYeHoursDao.getCirclePic();
    }
}
