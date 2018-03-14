package com.han.wanandroid.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.han.wanandroid.R;
import com.han.wanandroid.adapter.ArticleListAdapter;
import com.han.wanandroid.adapter.BannerPagerAdapter;
import com.han.wanandroid.base.BaseLazyFragment;
import com.han.wanandroid.model.pojo.ArticleBean;
import com.han.wanandroid.presenter.RecommendPresenter;
import com.han.wanandroid.utils.DensityUtils;
import com.han.wanandroid.utils.LogUtils;
import com.han.wanandroid.iview.IRecommendView;
import com.han.wanandroid.utils.image.GlideApp;
import com.han.wanandroid.widget.SimpleBanner;

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
       /* ViewPager viewPager = new ViewPager(context);
        viewPager.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(context, 200)));
        List<ImageView> banners = new ArrayList<>();
        for (int i = 0; i < 15; i++) {

            ImageView imageView = new ImageView(context);
            GlideApp.with(context).load(R.mipmap.ic_launcher).into(imageView);
            banners.add(imageView);
        }
                BannerPagerAdapter bannerPagerAdapter = new BannerPagerAdapter(banners);
viewPager.setAdapter(bannerPagerAdapter);
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2);
        */

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

        articleListAdapter.addHeaderView(new SimpleBanner(context));
    }


    @Override
    public void loadMore(List<ArticleBean> list) {
        pageIndex++;
        LogUtils.e(TAG, "loadMore");
        articleListAdapter.addData(list);
        articleListAdapter.loadMoreComplete();
    }


}
