package com.gototongcheng.mapping;


import com.gototongcheng.dao.MainShouYeDao;
import com.gototongcheng.model.MainShouYeCircleModel;
import com.gototongcheng.model.MainShouYeFirstGridViewModel;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * 首页连接后台
 * Created by zhyan on 16/6/11.
 */
public class MainShouYeMapper extends BaseMapper{
    private MainShouYeDao mainShouYeDao;
    public MainShouYeMapper(){
        initOkHttpClient();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(TEST_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mainShouYeDao = mRetrofit.create(MainShouYeDao.class);
    }

    public Observable<MainShouYeCircleModel> getCirclePic()
    {
        return mainShouYeDao.getCirclePic();
    }

    public Observable<MainShouYeFirstGridViewModel> getMainShouYeFirstGridView()
    {
        return mainShouYeDao.getMainShouYeFirstGridView();
    }
}
