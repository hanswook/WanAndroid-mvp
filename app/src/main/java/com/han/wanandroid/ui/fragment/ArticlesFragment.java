package com.han.wanandroid.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.han.wanandroid.R;
import com.han.wanandroid.base.BaseLazyFragment;
import com.han.wanandroid.mvp.presenter.ArticlePresenter;
import com.han.wanandroid.utils.LogUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesFragment extends BaseLazyFragment<ArticlePresenter> {


    public ArticlesFragment() {
        // Required empty public constructor
    }


    @Override
    protected ArticlePresenter loadPresenter() {
        return new ArticlePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_articles;
    }

    @Override
    protected void init() {
        LogUtils.e(TAG,"init ArticlesFragment");

    }

    @Override
    protected void fetchData() {
        LogUtils.e(TAG,"fetchData ArticlesFragment");

    }
}
