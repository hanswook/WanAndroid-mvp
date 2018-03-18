package com.han.wanandroid.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.han.wanandroid.R;
import com.han.wanandroid.adapter.ArticleListAdapter;
import com.han.wanandroid.base.BaseLazyFragment;
import com.han.wanandroid.model.pojo.ArticleBean;
import com.han.wanandroid.model.pojo.BannerBean;
import com.han.wanandroid.presenter.RecommendPresenter;
import com.han.wanandroid.utils.DensityUtils;
import com.han.wanandroid.utils.LogUtils;
import com.han.wanandroid.iview.IRecommendView;
import com.han.wanandroid.utils.ToastUtils;
import com.han.wanandroid.utils.image.GlideApp;
import com.han.wanandroid.widget.FlowLayout;
import com.han.widget.simplebanner.ImageLoader;
import com.han.widget.simplebanner.OnItemClickListener;
import com.han.widget.simplebanner.SimpleBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends BaseLazyFragment<RecommendPresenter> implements IRecommendView {

    @BindView(R.id.recommend_recycler)
    RecyclerView recommendRecycler;

    private ArticleListAdapter articleListAdapter;
    private int pageIndex = 0;
    private SimpleBanner simpleBanner;

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
        initBannerView();
        mPresenter.getBannerData();
    }

    private void initBannerView() {
        simpleBanner = new SimpleBanner(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(context, 200));
        simpleBanner.setLayoutParams(layoutParams);
        simpleBanner.setImageLoader(new ImageLoader() {
            @Override
            public void loadImage(String s, ImageView imageView) {
                GlideApp.with(context).load(s).into(imageView);
            }
        });
        simpleBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int i) {
                ToastUtils.show("i:" + i);
            }
        });
    }


    private void initBanner(List<String> imageUrlList, List<String> titleList) {
        simpleBanner.initBanner(imageUrlList, titleList);
        articleListAdapter.addHeaderView(simpleBanner);
    }

    private void initRecycler() {
        articleListAdapter = new ArticleListAdapter(R.layout.recommend_recycler_item_layout, null);
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

    @Override
    public void loadBanner(List<BannerBean> data) {
        LogUtils.e(TAG, "loadBanner data:" + data.size());
        List<String> imageUrlList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            imageUrlList.add(data.get(i).getImagePath());
            titleList.add(data.get(i).getTitle());
        }
        initBanner(imageUrlList, titleList);
    }


}
