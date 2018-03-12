package com.han.wanandroid.base;

/**
 * Created by hans
 * e-mail: hxxx1992@163.com
 */

public abstract class BasePresenter<T extends IBaseView> {

    protected T mView;
    protected String TAG;

    protected void attachView(T mView) {
        this.mView = mView;
        TAG = getClass().getSimpleName();
    }

    protected void dettachView() {
        mView = null;

    }

}
