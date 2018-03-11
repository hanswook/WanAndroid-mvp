package com.han.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.han.wanandroid.utils.LogUtils;


/**
 * Created by hans
 * date: 2018/3/8 20:01.
 * e-mail: hxxx1992@163.com
 * note：懒加载fragment目前适用于viewpager配合。
 */

public abstract class BaseLazyFragment<T extends BasePresenter> extends BaseCoreFragment<T> {

    private boolean mIsViewInitiated;
    private boolean mIsVisibleToUser;
    private boolean mIsDataInitiated;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisibleToUser = isVisibleToUser;
        initFetchData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mIsViewInitiated = true;
        initFetchData();
    }

    private void initFetchData() {
        if (mIsVisibleToUser && mIsViewInitiated && !mIsDataInitiated) {
            fetchData();
            mIsDataInitiated = true;
        }
    }

    protected abstract void fetchData();

    @Override
    protected void init() {

    }
}
