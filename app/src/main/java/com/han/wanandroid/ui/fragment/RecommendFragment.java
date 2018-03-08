package com.han.wanandroid.ui.fragment;


import android.support.v4.app.Fragment;

import com.han.wanandroid.R;
import com.han.wanandroid.base.BaseLazyFragment;
import com.han.wanandroid.mvp.presenter.RecommendPresenter;
import com.han.wanandroid.utils.LogUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends BaseLazyFragment<RecommendPresenter> {


    public RecommendFragment() {
    }

    @Override
    protected RecommendPresenter loadPresenter() {
        return new RecommendPresenter();
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void init() {
        LogUtils.e(TAG,"init RecommendFragment");
    }

    @Override
    protected void fetchData() {
        LogUtils.e(TAG,"fetchData RecommendFragment");

    }
}
