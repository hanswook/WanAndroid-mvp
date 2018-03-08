package com.han.wanandroid.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;

import com.han.wanandroid.R;
import com.han.wanandroid.adapter.HomeFragmentPagerAdapter;
import com.han.wanandroid.base.BaseActivity;
import com.han.wanandroid.mvp.presenter.HomePresenter;
import com.han.wanandroid.mvp.presenter.RecommendPresenter;
import com.han.wanandroid.mvp.view.IHomeView;
import com.han.wanandroid.ui.fragment.ArticlesFragment;
import com.han.wanandroid.ui.fragment.RecommendFragment;
import com.han.wanandroid.ui.fragment.UserCenterFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        RecommendFragment recommendFragment = new RecommendFragment();
        ArticlesFragment recommendFragment2 = new ArticlesFragment();
        UserCenterFragment recommendFragment3 = new UserCenterFragment();
        fragments = new ArrayList<>();
        fragments.add(recommendFragment);
        fragments.add(recommendFragment2);
        fragments.add(recommendFragment3);
        homeViewpager.setAdapter(new HomeFragmentPagerAdapter(getSupportFragmentManager(),fragments));

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }


}
