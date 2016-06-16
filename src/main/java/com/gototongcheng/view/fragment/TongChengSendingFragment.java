package com.gototongcheng.view.fragment;

import android.app.Activity;
import android.view.View;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.MainActivityPresenter;
import com.gototongcheng.Presenter.TongChengSendingFragmentPresenter;
import com.gototongcheng.application.R;

/**
 * Created by zhyan on 16/6/16.
 */
public class TongChengSendingFragment extends BaseFragment {



    private CommonTopBarPresenter commonTopBarPresenter;
    private TongChengSendingFragmentPresenter tongChengSendingFragmentPresenter;

    public TongChengSendingFragment(){

    }
    public TongChengSendingFragment(Activity activity){
        this.activity = activity;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_tongcheng_sending_sv;
    }

    @Override
    public void initViews() {
        commonTopBarPresenter = new CommonTopBarPresenter(activity);
        commonTopBarPresenter.topBarCommonWidget.rlyLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tongChengSendingFragmentPresenter.back();
            }
        });
        mainActivityPresenter = new MainActivityPresenter(activity,R.id.fly_content);
        tongChengSendingFragmentPresenter = new TongChengSendingFragmentPresenter(activity);
        tongChengSendingFragmentPresenter.widget.llyTongchengSendingSender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityPresenter.showFragment(new TongChengSendingBuildAddressFragment(activity,"send"));
            }
        });
        tongChengSendingFragmentPresenter.widget.llyTongchengSendingReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityPresenter.showFragment(new TongChengSendingBuildAddressFragment(activity,"receive"));
            }
        });
    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("tongchengsending");
    }



}
