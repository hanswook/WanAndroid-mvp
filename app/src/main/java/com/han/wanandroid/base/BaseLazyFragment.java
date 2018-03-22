package com.han.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * Created by hans
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
