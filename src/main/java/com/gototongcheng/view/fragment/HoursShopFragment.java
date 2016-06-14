package com.gototongcheng.view.fragment;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.HoursFragmentPresenter;
import com.gototongcheng.application.R;
import com.gototongcheng.widget.textview.MaequeeTextView;

import butterknife.Bind;

/**
 * Created by zhyan on 16/6/13.
 */
public class HoursShopFragment extends BaseFragment implements View.OnClickListener{
    @Bind(R.id.mtv_hoursshop)
    MaequeeTextView maequeeTextView;
    private CommonTopBarPresenter commonTopBarPresenter;
    private HoursFragmentPresenter hoursFragmentPresenter;
    private Activity activity;
    public HoursShopFragment(){

    }
    public HoursShopFragment(Activity activity){
        this.activity = activity;
        commonTopBarPresenter = new CommonTopBarPresenter(activity);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_main_24hours_lly;
    }

    @Override
    public void initViews() {
        start(getView());
        hoursFragmentPresenter = new HoursFragmentPresenter(activity);
        commonTopBarPresenter.topBarSelectWidget.rlyLeft.setOnClickListener(this);
    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("24hours");
    }

    public void start(View v){
        maequeeTextView.startScroll();
    }
    public void stop(View v){
        maequeeTextView.stopScroll();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rly_left_select:
                hoursFragmentPresenter.back();
                break;
        }
    }
}
