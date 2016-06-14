package com.gototongcheng.view.fragment;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.gototongcheng.Presenter.CommonTopBarPresenter;
import com.gototongcheng.Presenter.FoodsFragmentPresenter;
import com.gototongcheng.application.R;

/**
 * Created by admin on 16/6/13.
 */
public class FoodsFragment extends BaseFragment implements View.OnClickListener{
    private Activity activity;
    private CommonTopBarPresenter commonTopBarPresenter;
    private FoodsFragmentPresenter foodsFragmentPresenter;
    public FoodsFragment(){

    }
    public FoodsFragment(Activity activity){
        this.activity = activity;
        commonTopBarPresenter = new CommonTopBarPresenter(activity);
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_parts_main_shouye_foods_lly;
    }

    @Override
    public void initViews() {
        foodsFragmentPresenter = new FoodsFragmentPresenter(activity);
        commonTopBarPresenter.topBarSelectWidget.rlyLeft.setOnClickListener(this);
    }

    @Override
    public void initToolBar() {
        commonTopBarPresenter.initTopBar("foods");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rly_left_select:
        //        Toast.makeText(activity,"this is foods",Toast.LENGTH_LONG).show();
                foodsFragmentPresenter.back();
                break;
        }

    }
}
