package com.han.wanandroid.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.han.wanandroid.R;
import com.han.wanandroid.adapter.RecommendAdapter;
import com.han.wanandroid.base.BaseLazyFragment;
import com.han.wanandroid.model.pojo.ArticleBean;
import com.han.wanandroid.presenter.RecommendPresenter;
import com.han.wanandroid.net.DefaultObserver;
import com.han.wanandroid.utils.LogUtils;
import com.han.wanandroid.view.IRecommendView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends BaseLazyFragment<RecommendPresenter> implements IRecommendView {

    @BindView(R.id.recommend_recycler)
    RecyclerView recommendRecycler;
    private List<ArticleBean> datas;

    private RecommendAdapter recommendAdapter;

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
        recommendAdapter = new RecommendAdapter(R.layout.recommend_recycler_item_layout, datas);
        recommendRecycler.setAdapter(recommendAdapter);
        recommendRecycler.setLayoutManager(new LinearLayoutManager(context));
        mPresenter.getData();
    }


    @Override
    public void loadRecycler(List<ArticleBean> list) {
        LogUtils.e(TAG, "loadRecycler");
        recommendAdapter.addData(list);
    }
}
