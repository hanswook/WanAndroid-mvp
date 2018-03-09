package com.han.wanandroid.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.han.wanandroid.R;
import com.han.wanandroid.base.BaseLazyFragment;
import com.han.wanandroid.presenter.ArticlePresenter;
import com.han.wanandroid.utils.LogUtils;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesFragment extends BaseLazyFragment<ArticlePresenter> {


    @BindView(R.id.article_recycler)
    RecyclerView articleRecycler;

    public ArticlesFragment() {
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
    protected void fetchData() {
        LogUtils.e(TAG, "fetchData ArticlesFragment");
        initRecycler();
    }

    private void initRecycler() {
    }

}
