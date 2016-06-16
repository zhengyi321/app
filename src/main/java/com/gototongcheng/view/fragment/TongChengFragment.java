package com.gototongcheng.view.fragment;

import android.app.Activity;
import android.view.View;

import com.gototongcheng.Presenter.CommonSelectorBarPresenter;
import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.TongChengFragmentPresenter;
import com.gototongcheng.application.R;

/**
 * Created by zhyan on 16/6/13.
 */
public class TongChengFragment extends BaseFragment {

    private CommonTopBarPresenter commonTopBarPresenter;
    private CommonSelectorBarPresenter commonSelectorBarPresenter;
    private TongChengFragmentPresenter tongChengFragmentPresenter;
    public  TongChengFragment(){

    }
    public TongChengFragment(Activity activity){
        this.activity = activity;

    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_shouye_tongcheng_sv;
    }

    @Override
    public void initViews() {
        commonTopBarPresenter = new CommonTopBarPresenter(activity);
        commonSelectorBarPresenter = new CommonSelectorBarPresenter(activity);
        tongChengFragmentPresenter = new TongChengFragmentPresenter(activity);
        commonTopBarPresenter.topBarCommonWidget.rlyLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tongChengFragmentPresenter.back();
            }
        });
        tongChengFragmentPresenter.widget.llyTongchengSending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tongChengFragmentPresenter.page("tongchengsending");
            }
        });
        tongChengFragmentPresenter.widget.llyTongchengGoodsTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tongChengFragmentPresenter.page("tongchenggoodstracking");
            }
        });
        tongChengFragmentPresenter.widget.llyTongchengFeeCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tongChengFragmentPresenter.page("tongchengfeecheck");
            }
        });
        tongChengFragmentPresenter.widget.llyTongchengTimeCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tongChengFragmentPresenter.page("tongchengtimecheck");
            }
        });
        tongChengFragmentPresenter.widget.llyTongchengSiteCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tongChengFragmentPresenter.page("tongchengsitecheck");
            }
        });
        tongChengFragmentPresenter.widget.llyTongchengRangCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tongChengFragmentPresenter.page("tongchengrangcheck");
            }
        });

    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("tongcheng");
        commonSelectorBarPresenter.initSelectStyle("tongcheng");
    }


}
