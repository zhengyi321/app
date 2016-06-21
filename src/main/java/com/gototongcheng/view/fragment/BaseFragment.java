package com.gototongcheng.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gototongcheng.Presenter.MainActivityPresenter;

import butterknife.ButterKnife;

/**
 * Created by zhyan on 16/6/10.
 */
public abstract class BaseFragment extends Fragment {
    private View rootView;
    protected Activity activity;
    protected MainActivityPresenter mainActivityPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater,container,savedInstanceState);
        rootView = inflater.inflate(getLayoutId(), container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, activity);
        initViews();
        initToolBar();

    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }

    public abstract int getLayoutId();

    public abstract void initViews();

    public abstract void initToolBar();
}
