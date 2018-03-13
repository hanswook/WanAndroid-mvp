package com.han.wanandroid.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.han.wanandroid.R;
import com.han.wanandroid.adapter.ArticleListAdapter;
import com.han.wanandroid.base.BaseLazyFragment;
import com.han.wanandroid.model.pojo.ArticleBean;
import com.han.wanandroid.presenter.RecommendPresenter;
import com.han.wanandroid.utils.LogUtils;
import com.han.wanandroid.iview.IRecommendView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends BaseLazyFragment<RecommendPresenter> implements IRecommendView {

    @BindView(R.id.recommend_recycler)
    RecyclerView recommendRecycler;
    private List<ArticleBean> datas;

    private ArticleListAdapter articleListAdapter;
    private int pageIndex = 0;

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
    protected void fetchData() {
        initRecycler();
    }

    private void initRecycler() {
        datas = new ArrayList<>();
        articleListAdapter = new ArticleListAdapter(R.layout.recommend_recycler_item_layout, datas);
        recommendRecycler.setAdapter(articleListAdapter);
        recommendRecycler.setLayoutManager(new LinearLayoutManager(context));
        mPresenter.getData(pageIndex);
        articleListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                LogUtils.e(TAG, "onLoadMoreRequested");
                mPresenter.getData(pageIndex);
            }
        }, recommendRecycler);
    }


    @Override
    public void loadMore(List<ArticleBean> list) {
        pageIndex++;
        LogUtils.e(TAG, "loadMore");
        articleListAdapter.addData(list);
        articleListAdapter.loadMoreComplete();
    }


}
