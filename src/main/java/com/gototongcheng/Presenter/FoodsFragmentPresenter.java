package com.gototongcheng.Presenter;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.gototongcheng.adapter.ShouYeFoodsRecycleViewAdapter;
import com.gototongcheng.application.R;
import com.gototongcheng.view.fragment.MainShouYeFragment;
import com.gototongcheng.widget.listener.AutoLoadOnScrollListener;
import com.gototongcheng.widget.progressview.CircleProgressView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 美食外送 逻辑处理
 * Created by zhyan on 16/6/14.
 */
public class FoodsFragmentPresenter extends BasePresenter{

    public FoodsFragmentWidget foodsFragmentWidget;
    private List<String> dataList;
    private AutoLoadOnScrollListener mAutoLoadOnScrollListener;
    private ShouYeFoodsRecycleViewAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;
    public FoodsFragmentPresenter(){

    }

    public FoodsFragmentPresenter(Activity activity){
        ButterKnife.bind(this,activity);
        initDataList();
        initViews(activity);
    }
    private void initDataList(){
        dataList = new ArrayList<String>();
        dataList.add("hello1");
        dataList.add("hello1");
        dataList.add("hello1");
        dataList.add("hello1");
        dataList.add("hello1");
        dataList.add("hello1");
        dataList.add("hello1");
        dataList.add("hello1");
        dataList.add("hello1");
        dataList.add("hello1");
        dataList.add("hello1");




    }
    protected void initViews(final Activity activity){
        this.activity = activity;
        adapter = new ShouYeFoodsRecycleViewAdapter(activity,dataList);
        mainActivityPresenter = new MainActivityPresenter(activity,R.id.fly_content);

        mLinearLayoutManager = new LinearLayoutManager(activity);
        foodsFragmentWidget = new FoodsFragmentWidget(activity);
        foodsFragmentWidget.rvFoods.setAdapter(adapter);
        foodsFragmentWidget.rvFoods.setLayoutManager(new LinearLayoutManager(activity));
        foodsFragmentWidget.slyFoodsRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {

            @Override
            public void onRefresh()
            {
                Toast.makeText(activity,"this is refresh",Toast.LENGTH_SHORT).show();
                mHandler.sendEmptyMessageDelayed(0, 1000);
            }
        });
        mAutoLoadOnScrollListener = new AutoLoadOnScrollListener(mLinearLayoutManager)
        {

            @Override
            public void onLoadMore(int currentPage)
            {

           //     loadMoreDaily(currentTime);


            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {

                super.onScrolled(recyclerView, dx, dy);
                //int firstPos = (recyclerView == null || recyclerView.getChildCount() == 0 ? 0 : recyclerView.getChildAt(0).getTop());
                if(dy > 0){
                    foodsFragmentWidget.btnFoodsRefresh.setVisibility(View.GONE);
                }else{
                    foodsFragmentWidget.btnFoodsRefresh.setVisibility(View.VISIBLE);
                }

                foodsFragmentWidget.slyFoodsRefresh.setEnabled(mLinearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0);
            }
        };
        foodsFragmentWidget.rvFoods.addOnScrollListener(mAutoLoadOnScrollListener);

        getLatesFoodsData(false);
    }


    public void back(){
        mainActivityPresenter.showFragment(new MainShouYeFragment(activity));
    }
    private void loadMoreDaily(final String currentTime)
    {

    }

    @OnClick(R.id.btn_foods_refresh)
    void refreshData()
    {
   //     Toast.makeText(activity,"this is btn_foods_refresh",Toast.LENGTH_SHORT).show();
        //回到顶部
      //  mLinearLayoutManager.scrollToPosition(1);
        foodsFragmentWidget.rvFoods.scrollToPosition(0);
    }

    private Handler mHandler = new Handler()
    {

        @Override
        public void handleMessage(Message msg)
        {

            super.handleMessage(msg);
            if (msg.what == 0)
            {
                getLatesFoodsData(true);
            } else if (msg.what == 1)
            {
                hideProgress();
                foodsFragmentWidget.slyFoodsRefresh.setRefreshing(false);
                finishGetFoods();
            }
        }
    };
    public void getLatesFoodsData(final boolean isDownRefresh)
    {
        if (!isDownRefresh)
        {
            showProgress();
        }
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }
    private void showProgress()
    {

        foodsFragmentWidget.circleProgress.setVisibility(View.VISIBLE);
        foodsFragmentWidget.circleProgress.spin();
        foodsFragmentWidget.rvFoods.setVisibility(View.GONE);
    }

    public void hideProgress()
    {

        foodsFragmentWidget.circleProgress.setVisibility(View.GONE);
        foodsFragmentWidget.circleProgress.stopSpinning();
        foodsFragmentWidget.rvFoods.setVisibility(View.VISIBLE);
    }

    private void finishGetFoods()
    {
     //   foodsFragmentWidget.rvFoods.setAdapter(mHeaderViewRecyclerAdapter);


    }
    public class FoodsFragmentWidget{
        public FoodsFragmentWidget(Activity activity){
            ButterKnife.bind(this,activity);
        }

        @Bind(R.id.rv_foods)
         RecyclerView rvFoods;
        @Bind(R.id.sly_foods_refresh)
         SwipeRefreshLayout slyFoodsRefresh;
        @Bind(R.id.btn_foods_refresh)
         FloatingActionButton btnFoodsRefresh;
        @Bind(R.id.circle_progress)
        CircleProgressView circleProgress;
    }
}
