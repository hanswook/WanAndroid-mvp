package com.han.wanandroid.ui.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.han.wanandroid.R;
import com.han.wanandroid.adapter.ArticleListAdapter;
import com.han.wanandroid.base.BaseLazyFragment;
import com.han.wanandroid.model.pojo.ArticleBean;
import com.han.wanandroid.model.pojo.TreeBean;
import com.han.wanandroid.presenter.ArticlePresenter;
import com.han.wanandroid.utils.LogUtils;
import com.han.wanandroid.iview.IArticleView;
import com.han.wanandroid.widget.FlowLayout;

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
    @BindView(R.id.article_flow)
    FlowLayout articleFlow;


    private List<TreeBean<TreeBean>> tabsData;

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
        initView();
        LogUtils.e(TAG, "fetchData ArticlesFragment");
        mPresenter.getTabData();

    }

    private void initView() {
        initTabs();
        initRecycler();
    }

    private void initData() {
        tabsData = new ArrayList<>();
    }

    private void initTabs() {
        articleTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        articleTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtils.e(TAG, "onTabSelected position:" + tab.getPosition());
                showFlow(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                LogUtils.e(TAG, "onTabUnselected position:" + tab.getPosition());

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                LogUtils.e(TAG, "onTabReselected position:" + tab.getPosition());

            }
        });
    }


    @Override
    public void loadTabsData(List<TreeBean<TreeBean>> data) {
        tabsData.addAll(data);
        initPrimaryTabLayout();

    }

    private void initRecycler() {
        articleListAdapter = new ArticleListAdapter(R.layout.recommend_recycler_item_layout, null);
        articleRecycler.setAdapter(articleListAdapter);
        articleRecycler.setLayoutManager(new LinearLayoutManager(context));

    }


    @Override
    public void loadRecyclerData(List<ArticleBean> datas) {
        LogUtils.e(TAG, "loadRecyclerData:" + datas.size());
        articleListAdapter.setNewData(datas);
        articleListAdapter.notifyDataSetChanged();
    }

    private void initPrimaryTabLayout() {
        for (int i = 0; i < tabsData.size(); i++) {
            articleTab.addTab(articleTab.newTab().setText(tabsData.get(i).getName()));
        }
        LogUtils.e(TAG, "position:" + articleTab.getTabAt(0).isSelected());

        mPresenter.getRecyclerData(tabsData.get(0).getChildren().get(0).getId());

    }

    public void showFlow(int position) {
        articleFlow.removeAllViews();
        for (int i = 0; i < tabsData.get(position).getChildren().size(); i++) {
            TextView textView = (TextView) TextView.inflate(context, R.layout.tag_text_layout, null);
            textView.setText(tabsData.get(position).getChildren().get(i).getName());
            articleFlow.addView(textView);
        }
        articleFlow.setVisibility(View.VISIBLE);
    }


    public void hideFlow() {
        articleFlow.setVisibility(View.INVISIBLE);
    }

}
