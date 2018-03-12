package com.han.wanandroid.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.han.wanandroid.R;
import com.han.wanandroid.adapter.HomeFragmentPagerAdapter;
import com.han.wanandroid.base.BaseActivity;
import com.han.wanandroid.presenter.HomePresenter;
import com.han.wanandroid.view.IHomeView;
import com.han.wanandroid.ui.fragment.ArticlesFragment;
import com.han.wanandroid.ui.fragment.RecommendFragment;
import com.han.wanandroid.ui.fragment.UserCenterFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity<HomePresenter> implements IHomeView {


    List<Fragment> fragments;

    @BindView(R.id.home_viewpager)
    ViewPager homeViewpager;

    @Override
    protected HomePresenter loadPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void init() {
        mPresenter.defaultLogin();
        RecommendFragment recommendFragment = new RecommendFragment();
        ArticlesFragment recommendFragment2 = new ArticlesFragment();
        UserCenterFragment recommendFragment3 = new UserCenterFragment();
        fragments = new ArrayList<>();
        fragments.add(recommendFragment);
        fragments.add(recommendFragment2);
        fragments.add(recommendFragment3);
        homeViewpager.setAdapter(new HomeFragmentPagerAdapter(getSupportFragmentManager(), fragments));

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected boolean isFullScreen() {
        return true;
    }
}
