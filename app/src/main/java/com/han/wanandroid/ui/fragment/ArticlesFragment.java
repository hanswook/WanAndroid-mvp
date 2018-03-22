package com.han.wanandroid.ui.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.han.wanandroid.R;
import com.han.wanandroid.adapter.ArticleListAdapter;
import com.han.wanandroid.base.BaseLazyFragment;
import com.han.wanandroid.model.pojo.ArticleBean;
import com.han.wanandroid.model.pojo.TreeBean;
import com.han.wanandroid.presenter.ArticlePresenter;
import com.han.wanandroid.utils.OpenWebHelper;
import com.han.wanandroid.utils.baseutils.LogUtils;
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


    private ArticleListAdapter articleListAdapter;
    private List<TreeBean<TreeBean>> tabDatas;
    private List<ArticleBean> articleDatas;
    private TextView selectedText;

    private boolean isFirstShowFlow = true;
    private int currentTreeId = 0;

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
        tabDatas = new ArrayList<>();
        articleDatas = new ArrayList<>();
        initView();
        LogUtils.e(TAG, "fetchData ArticlesFragment");
        mPresenter.getTabData();

    }

    private void initView() {
        initTabs();
        initRecycler();
    }


    private void initTabs() {
        articleTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        articleTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtils.e(TAG, "onTabSelected position:" + tab.getPosition());
                int position = tab.getPosition();
                if (tabDatas == null) {
                    return;
                }
                if (isFirstShowFlow) {
                    mPresenter.getRecyclerData(tabDatas.get(0).getChildren().get(0).getId());
                    currentTreeId = tabDatas.get(0).getChildren().get(0).getId();
                    isFirstShowFlow = false;
                } else {
                    showFlow(tabDatas.get(position).getChildren());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                LogUtils.e(TAG, "onTabUnselected position:" + tab.getPosition());

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                LogUtils.e(TAG, "onTabReselected position:" + tab.getPosition());
                if (articleFlow.getVisibility() != View.VISIBLE) {
                    if (articleFlow.getChildCount() <= 0) {
                        showFlow(tabDatas.get(tab.getPosition()).getChildren());
                    }
                    articleFlow.setVisibility(View.VISIBLE);
                } else {
                    articleFlow.setVisibility(View.GONE);

                }
            }
        });
    }

    private void setTagSelected(TextView view) {
        view.setBackground(getResources().getDrawable(R.drawable.tab_style_selected));
        view.setTextColor(getResources().getColor(R.color.white));
        selectedText = view;
    }

    private void setTagUnselected(TextView view) {
        view.setBackground(getResources().getDrawable(R.drawable.tab_style_unselected));
        view.setTextColor(getResources().getColor(R.color.black));
    }


    @Override
    public void loadTabsData(List<TreeBean<TreeBean>> data) {
        tabDatas = data;
        for (int i = 0; i < data.size(); i++) {
            articleTab.addTab(articleTab.newTab().setText(data.get(i).getName()));
        }
        LogUtils.e(TAG, "position:" + articleTab.getTabAt(0).isSelected());

    }

    private void initRecycler() {
        articleListAdapter = new ArticleListAdapter(R.layout.recommend_recycler_item_layout, articleDatas);
        articleRecycler.setAdapter(articleListAdapter);
        articleRecycler.setLayoutManager(new LinearLayoutManager(context));
        articleListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OpenWebHelper.openWeb(context, articleDatas.get(position).getLink());
            }
        });

    }


    @Override
    public void loadRecyclerData(List<ArticleBean> datas) {
        LogUtils.e(TAG, "loadRecyclerData:" + datas.size());
        articleDatas.addAll(datas);
        articleListAdapter.setNewData(datas);
        articleListAdapter.notifyDataSetChanged();

    }

    @Override
    public void showFlow(List<TreeBean> data) {
        articleFlow.removeAllViews();
        selectedText = null;
        for (int i = 0; i < data.size(); i++) {
            LayoutInflater inflater = getLayoutInflater();
            TextView textView = (TextView) inflater.inflate(R.layout.tag_text_layout, articleFlow, false);
            textView.setTag(data.get(i).getId());
            textView.setText(data.get(i).getName());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int treeId = (Integer) v.getTag();
                    if (currentTreeId == treeId) {
                        return;
                    }
                    mPresenter.getRecyclerData(treeId);
                    currentTreeId = treeId;
                    if (selectedText != null) {
                        setTagUnselected(selectedText);
                    }
                    setTagSelected((TextView) v);

                }
            });
            if (currentTreeId == data.get(i).getId()) {
                setTagSelected(textView);
                selectedText = textView;
            } else {
                setTagUnselected(textView);
            }
            articleFlow.addView(textView, -1);
        }
        articleFlow.setVisibility(View.VISIBLE);
    }


}
