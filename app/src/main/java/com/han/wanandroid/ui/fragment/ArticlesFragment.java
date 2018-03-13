package com.han.wanandroid.ui.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.han.wanandroid.R;
import com.han.wanandroid.adapter.ArticleListAdapter;
import com.han.wanandroid.base.BaseLazyFragment;
import com.han.wanandroid.model.pojo.ArticleBean;
import com.han.wanandroid.model.pojo.TreeBean;
import com.han.wanandroid.presenter.ArticlePresenter;
import com.han.wanandroid.utils.LogUtils;
import com.han.wanandroid.iview.IArticleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesFragment extends BaseLazyFragment<ArticlePresenter> implements IArticleView {


    @BindView(R.id.article_recycler)
    RecyclerView articleRecycler;
    @BindView(R.id.article_tab)
    TabLayout articleTab;


    private List<TreeBean<TreeBean>> TabsData;
    private List<ArticleBean> listData;

    private ArticleListAdapter articleListAdapter;


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
        initRecycler();
        initTabs();
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
        articleListAdapter = new ArticleListAdapter(R.layout.recommend_recycler_item_layout, listData);
        articleRecycler.setAdapter(articleListAdapter);
        articleRecycler.setLayoutManager(new LinearLayoutManager(context));

    }


    @Override
    public void loadRecyclerData(List<ArticleBean> datas) {
        LogUtils.e(TAG, "loadRecyclerData:"+datas.size());
        articleListAdapter.setNewData(datas);
        articleListAdapter.notifyDataSetChanged();
    }

    private void initPrimaryTabLayout() {
        for (int i = 0; i < TabsData.size(); i++) {
            articleTab.addTab(articleTab.newTab().setText(TabsData.get(i).getName()));
        }
        mPresenter.getRecyclerData(TabsData.get(0).getChildren().get(0).getId());
    }


}
