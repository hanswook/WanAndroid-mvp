package com.han.wanandroid.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.han.wanandroid.R;
import com.han.wanandroid.base.BaseLazyFragment;
import com.han.wanandroid.mvp.presenter.UserCenterPresenter;
import com.han.wanandroid.utils.LogUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserCenterFragment extends BaseLazyFragment<UserCenterPresenter> {


    public UserCenterFragment() {
        // Required empty public constructor
    }


    @Override
    protected void fetchData() {
        LogUtils.e(TAG,"fetchData UserCenterFragment");

    }

    @Override
    protected UserCenterPresenter loadPresenter() {
        return new UserCenterPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_center;
    }

    @Override
    protected void init() {
        LogUtils.e(TAG,"init UserCenterFragment");

    }
}
