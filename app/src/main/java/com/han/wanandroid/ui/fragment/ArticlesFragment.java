package com.han.wanandroid.ui.fragment;


import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.han.wanandroid.R;
import com.han.wanandroid.adapter.RecommendAdapter;
import com.han.wanandroid.base.BaseLazyFragment;
import com.han.wanandroid.model.pojo.ArticleBean;
import com.han.wanandroid.model.pojo.TreeBean;
import com.han.wanandroid.presenter.ArticlePresenter;
import com.han.wanandroid.utils.LogUtils;
import com.han.wanandroid.view.IArticleView;
import com.han.wanandroid.widget.IconFontTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesFragment extends BaseLazyFragment<ArticlePresenter> implements IArticleView {


    @BindView(R.id.article_recycler)
    RecyclerView articleRecycler;
    @BindView(R.id.article_return)
    IconFontTextView articleReturn;
    @BindView(R.id.article_title)
    TextView articleTitle;
    @BindView(R.id.article_topbar_layout)
    RelativeLayout articleTopbarLayout;
    @BindView(R.id.article_llayout)
    CoordinatorLayout articleLlayout;
    @BindView(R.id.article_tab)
    TabLayout articleTab;


    private List<TreeBean<TreeBean>> TabsData;
    private List<ArticleBean> listData;

    private RecommendAdapter recommendAdapter;


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
        initData();
        LogUtils.e(TAG, "fetchData ArticlesFragment");
        initTabs();
        initRecycler();
    }

    private void initData() {
        TabsData = new ArrayList<>();
        listData = new ArrayList<>();
    }

    private void initTabs() {
        articleTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        mPresenter.getTabData();
    }


    @Override
    public void loadTabsData(List<TreeBean<TreeBean>> data) {
        TabsData.addAll(data);
        initPrimaryTabLayout();

    }

    private void initRecycler() {
        listData = new ArrayList<>();
        recommendAdapter = new RecommendAdapter(R.layout.recommend_recycler_item_layout, datas);
        recommendRecycler.setAdapter(recommendAdapter);
        recommendRecycler.setLayoutManager(new LinearLayoutManager(context));
        mPresenter.getData(pageIndex);
        recommendAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                LogUtils.e(TAG, "onLoadMoreRequested");
                mPresenter.getData(pageIndex);
            }
        }, recommendRecycler);
    }


    @Override
    public void loadMore(List<ArticleBean> list) {
        LogUtils.e(TAG, "loadMore");
        recommendAdapter.addData(list);
        recommendAdapter.loadMoreComplete();
    }

    @Override
    public void loadRecyclerData(List<ArticleBean> datas) {
        listData.clear();

        listData.addAll(datas);
    }

    private void initPrimaryTabLayout() {
        for (int i = 0; i < TabsData.size(); i++) {
            articleTab.addTab(articleTab.newTab().setText(TabsData.get(i).getName()));
        }
    }


}
