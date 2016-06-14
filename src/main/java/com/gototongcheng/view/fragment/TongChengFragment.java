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
public class TongChengFragment extends BaseFragment implements View.OnClickListener{
    private Activity activity;
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
        return R.layout.fragment_parts_main_shouye_tongcheng_lly;
    }

    @Override
    public void initViews() {
        commonTopBarPresenter = new CommonTopBarPresenter(activity);
        commonSelectorBarPresenter = new CommonSelectorBarPresenter(activity);
        tongChengFragmentPresenter = new TongChengFragmentPresenter(activity);
        commonTopBarPresenter.topBarCommonWidget.rlyLeft.setOnClickListener(this);
    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("tongcheng");
        commonSelectorBarPresenter.initStyle("tongcheng");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rly_left:
                tongChengFragmentPresenter.back();
                break;
        }
    }
}
