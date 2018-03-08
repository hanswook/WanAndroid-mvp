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

    protected boolean mIsViewInitiated;
    protected boolean mIsVisibleToUser;
    protected boolean mIsDataInitiated;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtils.e(TAG,"setUserVisibleHint :"+isVisibleToUser);
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
        LogUtils.e(TAG, "initFetchData mIsViewInitiated：" + mIsViewInitiated + ",mIsVisibleToUser:" + mIsVisibleToUser + ",mIsDataInitiated:" + mIsDataInitiated);
        if (mIsVisibleToUser && mIsViewInitiated && !mIsDataInitiated) {
            fetchData();
            mIsDataInitiated = true;
        }
    }

    protected abstract void fetchData();
}
